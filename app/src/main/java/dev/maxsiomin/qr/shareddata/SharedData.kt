package dev.maxsiomin.qr.shareddata

import android.os.Bundle

/**
 * Lets fragment save data in activity
 */
interface SharedData {

    fun getSharedString(key: StringSharedDataKey): String?

    fun putSharedString(key: StringSharedDataKey, value: String?)

    fun toBundle(): Bundle
}
