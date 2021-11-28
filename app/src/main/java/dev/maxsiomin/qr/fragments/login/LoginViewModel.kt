package dev.maxsiomin.qr.fragments.login

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.fragments.base.BaseViewModel
import dev.maxsiomin.qr.util.Email
import dev.maxsiomin.qr.util.Password
import dev.maxsiomin.qr.util.UiActions
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(uiActions: UiActions) : BaseViewModel(uiActions) {

    /**
     * Login via [FirebaseAuth]
     */
    fun login(email: Email, password: Password, auth: FirebaseAuth, onLogin: () -> Unit) {
        auth.signInWithEmailAndPassword(email.value, password.value).addOnCompleteListener { task ->
            if (task.isSuccessful)
                onLogin()
            else
                toast(R.string.unable_to_login, Toast.LENGTH_LONG)
        }
    }
}
