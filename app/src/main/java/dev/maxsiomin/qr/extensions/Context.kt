package dev.maxsiomin.qr.extensions

import android.content.Context
import androidx.preference.PreferenceManager
import dev.maxsiomin.qr.util.SharedPrefs

fun Context.getDefaultSharedPrefs(): SharedPrefs =
    PreferenceManager.getDefaultSharedPreferences(this)
