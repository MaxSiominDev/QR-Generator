package dev.maxsiomin.qr.fragments.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.databinding.FragmentHistoryBinding
import dev.maxsiomin.qr.fragments.base.BaseFragment
import dev.maxsiomin.qr.fragments.base.BaseViewModel
import dev.maxsiomin.qr.fragments.tabs.TabsFragmentDirections

/**
 * Fragment with history
 */
@AndroidEntryPoint
class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    private lateinit var binding: FragmentHistoryBinding

    override val mRoot get() = binding.root

    private val mViewModel by viewModels<BaseViewModel>()

    private lateinit var historyLoader: HistoryLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHistoryBinding.bind(view)

        historyLoader = HistoryLoader(mViewModel)
        historyLoader.itemsLiveData.observe(viewLifecycleOwner) { onHistoryUpdated(it) }
    }

    private fun onHistoryUpdated(history: List<HistoryLoader.PlaceholderItem>?) {
        with (binding) {
            // If there is no history in database
            if (history.isNullOrEmpty()) {
                historyIsEmptyTextView.visibility = View.VISIBLE
                return
            }

            // Set the adapter
            historyRecyclerView.layoutManager = LinearLayoutManager(context)

            historyRecyclerView.adapter = HistoryAdapter(mViewModel, history) {
                val direction = TabsFragmentDirections.actionTabsFragmentToQrFragment(it)
                parentFragment!!.parentFragment!!.findNavController().navigate(direction)
            }
        }
    }
}

