package dev.maxsiomin.qr.fragments.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.databinding.FragmentLoginBinding
import dev.maxsiomin.qr.extensions.clearError
import dev.maxsiomin.qr.extensions.sharedData
import dev.maxsiomin.qr.extensions.toEditable
import dev.maxsiomin.qr.fragments.base.BaseFragment
import dev.maxsiomin.qr.shareddata.SharedDataKeys.EMAIL
import dev.maxsiomin.qr.shareddata.SharedDataKeys.PASSWORD
import dev.maxsiomin.qr.util.Email
import dev.maxsiomin.qr.util.Password
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override val mRoot get() = binding.root

    private val mViewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (auth.currentUser != null)
            findNavController().navigate(R.id.action_loginFragment_to_tabsFragment)

        binding = FragmentLoginBinding.bind(view)

        with (binding) {
            // Restore state
            sharedData.getSharedString(EMAIL)?.let {
                emailEditText.text = it.toEditable()
            }

            // Restore state
            sharedData.getSharedString(PASSWORD)?.let {
                passwordEditText.text = it.toEditable()
            }

            loginButton.setOnClickListener {
                val email = Email(emailEditText.text)
                val password = Password(passwordEditText.text)

                when {
                    email.isNotCorrect -> {
                        emailEditTextLayout.error = getString(R.string.invalid_email)
                        emailEditText.requestFocus()
                    }

                    password.isEmpty -> {
                        passwordEditTextLayout.error = getString(R.string.password_not_provided)
                        passwordEditText.requestFocus()
                    }

                    else -> {
                        mViewModel.login(email, password, auth) {
                            onLogin()
                        }
                    }
                }
            }

            emailEditText.addTextChangedListener { emailEditTextLayout.clearError() }
            passwordEditText.addTextChangedListener { passwordEditTextLayout.clearError() }

            forgotPasswordTextView.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
            }

            signupTextView.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
        }
    }

    override fun onStop() {
        sharedData.putSharedString(EMAIL, binding.emailEditText.text?.toString())
        sharedData.putSharedString(PASSWORD, binding.passwordEditText.text?.toString())
        super.onStop()
    }

    private fun onLogin() {
        findNavController().navigate(R.id.action_loginFragment_to_tabsFragment)
    }
}
