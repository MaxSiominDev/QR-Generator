package dev.maxsiomin.qr.shareddata

import android.os.Bundle

/**
 * Implements [SharedData] interface
 */
class SharedDataImpl(bundle: Bundle?) : SharedData {

    private val sharedBundle: Bundle = bundle ?: Bundle()

    override fun getSharedString(key: StringSharedDataKey): String? =
        sharedBundle.getString(key.value)

    override fun putSharedString(key: StringSharedDataKey, value: String?) {
        if (value == null)
            sharedBundle.remove(key.value)
        else
            sharedBundle.putString(key.value, value)
    }

    override fun toBundle(): Bundle = sharedBundle
}
