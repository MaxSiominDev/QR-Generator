package dev.maxsiomin.qr.fragments.resetpassword

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.util.Email
import dev.maxsiomin.qr.util.UiActions
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(uiActions: UiActions) : ViewModel(), UiActions by uiActions {

    fun sendPasswordResetEmail(email: Email, auth: FirebaseAuth) {
        auth.sendPasswordResetEmail(email.value).addOnCompleteListener { task ->
            toast(
                if (task.isSuccessful) R.string.check_email else R.string.unable_to_send_reset_email,
                Toast.LENGTH_LONG,
            )
        }
    }
}
