package dev.maxsiomin.qr.extensions

import android.text.SpannableStringBuilder

fun String.toEditable() = SpannableStringBuilder(this)

/**
 * If string == null, returns ""
 */
fun String?.notNull(): String = this ?: ""
