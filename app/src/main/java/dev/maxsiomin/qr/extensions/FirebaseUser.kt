package dev.maxsiomin.qr.extensions

import com.google.firebase.auth.FirebaseUser

val FirebaseUser.isNotEmailVerified get() = !isEmailVerified
