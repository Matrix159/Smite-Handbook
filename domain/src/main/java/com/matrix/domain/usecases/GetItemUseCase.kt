package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.ItemInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemUseCase @Inject constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(itemId: Int): Flow<ItemInformation> = smiteRepository.getItem(itemId)
}
