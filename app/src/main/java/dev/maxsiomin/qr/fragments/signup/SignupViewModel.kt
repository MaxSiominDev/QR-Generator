package dev.maxsiomin.qr.fragments.signup

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.util.Email
import dev.maxsiomin.qr.util.Password
import dev.maxsiomin.qr.util.UiActions
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(uiActions: UiActions) : ViewModel(), UiActions by uiActions {

    fun signup(email: Email, password: Password, auth: FirebaseAuth, onSignup: () -> Unit) {
        auth.createUserWithEmailAndPassword(email.value, password.value).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignup()
            } else {
                toast(R.string.unable_to_signup, Toast.LENGTH_LONG)
            }
        }
    }
}
