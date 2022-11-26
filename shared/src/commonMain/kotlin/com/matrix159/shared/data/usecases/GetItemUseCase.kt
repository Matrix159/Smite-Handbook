package com.matrix159.shared.data.usecases

import com.matrix159.shared.data.contracts.SmiteRepository
import com.matrix159.shared.data.models.ItemInformation
import kotlinx.coroutines.flow.Flow

class GetItemUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(itemId: Int): Flow<ItemInformation> = smiteRepository.getItem(itemId)
}
