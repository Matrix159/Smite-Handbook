package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetLatestItemsUseCase: KoinComponent {
  private val smiteRepository by inject<SmiteRepository>()

  operator fun invoke(): Flow<List<ItemInformation>> = smiteRepository.getItems().map { items -> items.filter { it.activeFlag } }
}
