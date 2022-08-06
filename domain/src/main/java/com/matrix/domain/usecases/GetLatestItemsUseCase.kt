package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.Item
import javax.inject.Inject

class GetLatestItemsUseCase @Inject constructor(val smiteRepository: SmiteRepository) {
  suspend operator fun invoke(): List<Item> {
    return smiteRepository.getItems()
  }
}
