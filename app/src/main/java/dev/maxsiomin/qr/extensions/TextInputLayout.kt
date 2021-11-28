package dev.maxsiomin.qr.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearError() {
    if (error != null) error = null
}
