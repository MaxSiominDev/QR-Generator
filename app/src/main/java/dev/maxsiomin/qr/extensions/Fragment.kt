package dev.maxsiomin.qr.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import dev.maxsiomin.qr.activity.MainActivity
import dev.maxsiomin.qr.shareddata.SharedData

val Fragment.mainActivity: MainActivity get() = requireActivity() as MainActivity

val Fragment.sharedData: SharedData get() = mainActivity.sharedData

val Fragment.imm: InputMethodManager
    get() = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
