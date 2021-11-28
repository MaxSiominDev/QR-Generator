package dev.maxsiomin.qr.fragments.resetpassword

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.databinding.FragmentResetPasswordBinding
import dev.maxsiomin.qr.extensions.clearError
import dev.maxsiomin.qr.extensions.sharedData
import dev.maxsiomin.qr.extensions.toEditable
import dev.maxsiomin.qr.fragments.base.BaseFragment
import dev.maxsiomin.qr.shareddata.SharedDataKeys.EMAIL
import dev.maxsiomin.qr.util.Email

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment(R.layout.fragment_reset_password) {

    private lateinit var binding: FragmentResetPasswordBinding

    override val mRoot get() = binding.root

    private val mViewModel by viewModels<ResetPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentResetPasswordBinding.bind(view)

        with (binding) {
            sharedData.getSharedString(EMAIL)?.let {
                emailEditText.text = it.toEditable()
            }

            resetPasswordButton.setOnClickListener {
                val email = Email(emailEditText.text)

                if (email.isNotCorrect) {
                    emailEditTextLayout.error = getString(R.string.invalid_email)
                    emailEditText.requestFocus()
                } else {
                    mViewModel.sendPasswordResetEmail(email, FirebaseAuth.getInstance())
                }
            }

            emailEditText.addTextChangedListener {
                emailEditTextLayout.clearError()
            }
        }
    }

    override fun onStop() {
        sharedData.putSharedString(EMAIL, binding.emailEditText.text?.toString())
        super.onStop()
    }
}
