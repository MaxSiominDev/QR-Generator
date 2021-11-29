package dev.maxsiomin.qr.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

val Fragment.imm: InputMethodManager
    get() = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
