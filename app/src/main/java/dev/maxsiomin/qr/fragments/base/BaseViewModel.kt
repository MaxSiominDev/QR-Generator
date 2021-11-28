package dev.maxsiomin.qr.fragments.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.qr.util.UiActions
import javax.inject.Inject

/**
 * If a fragment or an activity uses only UiActions impl, don't create custom viewModel for it, use this
 */
@HiltViewModel
open class BaseViewModel @Inject constructor(uiActions: UiActions) : ViewModel(), UiActions by uiActions
