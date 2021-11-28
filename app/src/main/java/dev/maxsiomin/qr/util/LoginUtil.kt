package dev.maxsiomin.qr.util

import android.text.Editable
import android.util.Patterns

data class Email(
    val value: String,
) {
    constructor(editableValue: Editable?) : this(editableValue?.toString() ?: "")

    val isCorrect = value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()

    val isNotCorrect = !isCorrect
}

data class Password(
    val value: String,
) {
    constructor(editableValue: Editable?) : this(editableValue?.toString() ?: "")

    val isEmpty = value.isEmpty()
    val isStrong = value.length >= PASSWORD_MIN_LENGTH

    companion object {
        const val PASSWORD_MIN_LENGTH = 8
    }
}
