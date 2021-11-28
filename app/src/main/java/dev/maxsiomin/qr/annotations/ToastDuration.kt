package dev.maxsiomin.qr.annotations

import android.widget.Toast
import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(Toast.LENGTH_SHORT, Toast.LENGTH_LONG)
annotation class ToastDuration
