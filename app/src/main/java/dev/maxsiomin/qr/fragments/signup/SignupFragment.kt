package dev.maxsiomin.qr.fragments.signup

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.activity.DialogBuilder
import dev.maxsiomin.qr.databinding.FragmentSignupBinding
import dev.maxsiomin.qr.extensions.clearError
import dev.maxsiomin.qr.fragments.base.BaseFragment
import dev.maxsiomin.qr.util.Email
import dev.maxsiomin.qr.util.Password
import dev.maxsiomin.qr.util.Password.Companion.PASSWORD_MIN_LENGTH
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : BaseFragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding

    override val mRoot get() = binding.root

    private val mViewModel by viewModels<SignupViewModel>()

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignupBinding.bind(view)

        with (binding) {
            loginTextView.setOnClickListener { findNavController().popBackStack() }

            signupButton.setOnClickListener {
                val email = Email(emailEditText.text)
                val password = Password(passwordEditText.text)
                val confirmedPassword = Password(confirmPasswordEditText.text)

                when {
                    email.isNotCorrect -> {
                        emailEditTextLayout.error = getString(R.string.invalid_email)
                        emailEditText.requestFocus()
                    }

                    !password.isStrong -> {
                        passwordEditTextLayout.error = getString(R.string.weak_password, PASSWORD_MIN_LENGTH)
                        passwordEditText.requestFocus()
                    }

                    confirmedPassword != password -> {
                        confirmPasswordEditTextLayout.error = getString(R.string.passwords_not_match)
                    }

                    else -> {
                        mViewModel.signup(email, password, auth) {
                            auth.signInWithEmailAndPassword(email.value, password.value).addOnCompleteListener {
                                VerifyEmailDialog.newInstance().show(parentFragmentManager)
                            }
                        }
                    }
                }
            }

            emailEditText.addTextChangedListener {
                emailEditTextLayout.clearError()
            }

            passwordEditText.addTextChangedListener {
                passwordEditTextLayout.clearError()
            }

            confirmPasswordEditText.addTextChangedListener {
                confirmPasswordEditTextLayout.clearError()
            }
        }
    }

    /**
     * Suggest user to verify email. It's not mandatory
     */
    class VerifyEmailDialog : DialogFragment() {

        @Inject
        lateinit var auth: FirebaseAuth

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            val dialog = DialogBuilder(requireContext())
                .setMessage(R.string.want_verify_email)
                .setNegativeButton(R.string.no_thanks) { _, _ ->
                    goToTabsFragment()
                }
                .setPositiveButton(R.string.verify) { _, _ ->
                    auth.currentUser!!.sendEmailVerification()
                    goToTabsFragment()
                }
                .create()

            dialog.setCanceledOnTouchOutside(false)

            return dialog
        }

        private fun goToTabsFragment() {
            parentFragment!!.findNavController().navigate(R.id.action_signupFragment_to_tabsFragment)
        }

        fun show(manager: FragmentManager) {
            show(manager, TAG)
        }

        companion object {

            const val TAG = "VerifyEmailDialog"

            @JvmStatic
            fun newInstance() = VerifyEmailDialog()
        }
    }
}
