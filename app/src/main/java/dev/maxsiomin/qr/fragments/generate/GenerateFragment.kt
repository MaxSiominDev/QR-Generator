package dev.maxsiomin.qr.fragments.generate

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.database.HistoryElement
import dev.maxsiomin.qr.databinding.FragmentGenerateBinding
import dev.maxsiomin.qr.extensions.clearError
import dev.maxsiomin.qr.fragments.base.BaseFragment
import dev.maxsiomin.qr.fragments.base.BaseViewModel
import dev.maxsiomin.qr.fragments.tabs.TabsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenerateFragment : BaseFragment(R.layout.fragment_generate) {

    private lateinit var binding: FragmentGenerateBinding

    override val mRoot get() = binding.root

    private val mViewModel by viewModels<BaseViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGenerateBinding.bind(view)

        // If user changed text, clear error
        binding.qrCodeText.addTextChangedListener {
            binding.qrCodeTextLayout.clearError()
        }

        binding.generateButton.setOnClickListener {
            val text = binding.qrCodeText.text.toString()
            if (text.isEmpty()) {
                binding.qrCodeTextLayout.error = mViewModel.getString(R.string.invalid_value)
                binding.qrCodeText.requestFocus()
                return@setOnClickListener
            }

            // Create viewModel instance on the main thread
            mViewModel

            CoroutineScope(Dispatchers.IO).launch {
                pushHistory(HistoryElement(text))
            }

            (parentFragment!!.parentFragment as TabsFragment).showQrCode(text)
        }
    }

    private suspend fun pushHistory(elem: HistoryElement) {
        mViewModel.historyDao.insertQuery(elem)
    }
}
