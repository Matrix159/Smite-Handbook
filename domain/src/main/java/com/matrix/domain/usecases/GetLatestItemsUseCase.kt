package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.ItemInformation
import javax.inject.Inject

class GetLatestItemsUseCase @Inject constructor(val smiteRepository: SmiteRepository) {
  suspend operator fun invoke(): List<ItemInformation> {
    return smiteRepository.getItems()
  }
}
