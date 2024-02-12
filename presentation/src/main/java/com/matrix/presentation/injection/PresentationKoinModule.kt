package com.matrix.presentation.injection

import com.matrix.presentation.ui.builds.builddetails.BuildDetailsViewModel
import com.matrix.presentation.ui.builds.buildlist.BuildListViewModel
import com.matrix.presentation.ui.builds.createbuild.CreateBuildViewModel
import com.matrix.presentation.ui.gods.goddetails.GodDetailsViewModel
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.presentation.ui.items.itemdetails.ItemDetailsViewModel
import com.matrix.presentation.ui.items.itemlist.ItemListViewModel
import com.matrix.presentation.ui.builds.itemselection.ItemSelectionViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

fun presentationKoinModule() = module {
  viewModelOf(::GodListViewModel)
  viewModelOf(::GodDetailsViewModel)
  viewModelOf(::ItemListViewModel)
  viewModelOf(::ItemDetailsViewModel)
  viewModelOf(::ItemSelectionViewModel)
  viewModelOf(::BuildListViewModel)
  viewModel { (selectedGodId: Flow<Long?>, selectedItemIds: Flow<List<Long>>) -> CreateBuildViewModel(get(), selectedGodId, selectedItemIds) }
  viewModelOf(::BuildDetailsViewModel)
}