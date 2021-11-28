package dev.maxsiomin.qr.fragments.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.maxsiomin.qr.databinding.FragmentHistoryItemBinding
import dev.maxsiomin.qr.util.UiActions

/**
 * Recycler view adapter for [HistoryFragment]
 */
class HistoryAdapter(
    private val uiActions: UiActions,
    private val values: List<HistoryLoader.PlaceholderItem>,
    private val onItemClicked: (String) -> Unit,
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(), UiActions by uiActions {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            FragmentHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.qrCodeText.text = values[position].qrCodeText
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(binding: FragmentHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val qrCodeText = binding.qrCodeItem

        init {
            // If item clicked show qr code
            itemView.setOnClickListener {
                onItemClicked(qrCodeText.text.toString())
            }
        }
    }
}
