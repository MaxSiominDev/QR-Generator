package dev.maxsiomin.qr.fragments.qr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.databinding.FragmentQrBinding
import dev.maxsiomin.qr.fragments.base.BaseFragment
import dev.maxsiomin.qr.fragments.base.BaseViewModel

@AndroidEntryPoint
class QrFragment : BaseFragment(R.layout.fragment_qr) {

    private lateinit var binding: FragmentQrBinding

    override val mRoot get() = binding.root

    private val mViewModel by viewModels<BaseViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQrBinding.bind(view)

        setupQrCode()
    }

    private fun setupQrCode() {
        val qrCodeText: String = requireArguments().getString(ARG_QR_CODE_TEXT)!!
        val size = mViewModel.getDimen(R.dimen.qr_size)

        val matrix = MultiFormatWriter().encode(qrCodeText, BarcodeFormat.QR_CODE, size, size)
        binding.qrImage.setImageBitmap(BarcodeEncoder().createBitmap(matrix))
    }

    companion object {
        const val ARG_QR_CODE_TEXT = "QrCodeText"
    }
}
