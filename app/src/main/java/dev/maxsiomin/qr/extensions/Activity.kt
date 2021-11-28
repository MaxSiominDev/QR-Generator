package dev.maxsiomin.qr.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import dev.maxsiomin.qr.R

/**
 * Opens [url] via browser.
 * If browser not found, toasts [R.string.smth_went_wrong]
 */
fun Activity.openInBrowser(url: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, R.string.smth_went_wrong, Toast.LENGTH_LONG).show()
    }
}
