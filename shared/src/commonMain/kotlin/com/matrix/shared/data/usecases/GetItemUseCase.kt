package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.ItemInformation
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetItemUseCase: KoinComponent {
  private val smiteRepository by inject<SmiteRepository>()
  operator fun invoke(itemId: Int): Flow<ItemInformation> = smiteRepository.getItem(itemId)
}
