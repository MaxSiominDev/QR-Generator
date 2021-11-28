package dev.maxsiomin.qr.fragments.tabs

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.qr.R
import dev.maxsiomin.qr.databinding.FragmentTabsBinding
import dev.maxsiomin.qr.fragments.base.BaseFragment

@AndroidEntryPoint
class TabsFragment : BaseFragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding

    override val mRoot get() = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTabsBinding.bind(view)

        with (childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment) {
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        }
    }

    fun showQrCode(qrCodeText: String) {
        findNavController().navigate(
            TabsFragmentDirections.actionTabsFragmentToQrFragment(qrCodeText)
        )
    }
}
