package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.ItemInformation
import kotlinx.coroutines.flow.Flow

class GetItemUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(itemId: Int): Flow<ItemInformation> = smiteRepository.getItem(itemId)
}
