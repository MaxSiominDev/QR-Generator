package dev.maxsiomin.qr.fragments.history

import androidx.lifecycle.MutableLiveData
import dev.maxsiomin.qr.database.HistoryElement
import dev.maxsiomin.qr.util.UiActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Provides search history
 */
class HistoryLoader(uiActions: UiActions) : UiActions by uiActions {

    val itemsLiveData = MutableLiveData<List<PlaceholderItem>>()
    private val items = mutableListOf<PlaceholderItem>()

    init { CoroutineScope(Dispatchers.IO).launch { getHistory() } }

    /**
     * Loads history from Database and updates [itemsLiveData]
     */
    private suspend fun getHistory() {
        val history: List<HistoryElement> = historyDao.loadHistory()

        for (i in history.indices.reversed())
            items.add(createPlaceholderItem(i + 1, history[i].qrCodeText))

        itemsLiveData.postValue(items)
    }

    /**
     * Returns new [PlaceholderItem]
     */
    private fun createPlaceholderItem(position: Int, qrCodeText: String) =
        PlaceholderItem(position.toString(), qrCodeText)

    /**
     * A placeholder item representing a piece of content
     */
    data class PlaceholderItem(val id: String, val qrCodeText: String)
}
