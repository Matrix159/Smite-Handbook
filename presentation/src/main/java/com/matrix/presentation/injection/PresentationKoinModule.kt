package com.matrix.presentation.injection

import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

fun presentationKoinModule() = module {
  viewModelOf(::GodListViewModel)
}