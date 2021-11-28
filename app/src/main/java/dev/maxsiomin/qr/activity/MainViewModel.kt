package dev.maxsiomin.qr.activity

import android.widget.Toast
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.qr.BuildConfig
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.fragments.base.BaseViewModel
import dev.maxsiomin.qr.repository.Failure
import dev.maxsiomin.qr.repository.Success
import dev.maxsiomin.qr.repository.UpdateRepository
import dev.maxsiomin.qr.util.SharedPrefsConfig.DATE_UPDATE_SUGGESTED
import dev.maxsiomin.qr.util.UiActions
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(uiActions: UiActions) : BaseViewModel(uiActions) {

    /**
     * Checks for updates. If updates found calls [onUpdateFound]
     */
    fun checkForUpdates(onUpdateFound: (String) -> Unit) {

        if (LocalDate.now().toString() == sharedPrefs.getString(DATE_UPDATE_SUGGESTED, null))
            return

        UpdateRepository(this) { result ->
            if (result is Success) {
                val currentVersionName = BuildConfig.VERSION_NAME
                if (currentVersionName != result.latestVersionName) {
                    onUpdateFound(result.latestVersionName)
                }
            } else {
                toast(R.string.last_version_checking_failed, Toast.LENGTH_LONG)
                Timber.e((result as Failure).errorMessage)
            }
        }.getLastVersion()
    }
}
