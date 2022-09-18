package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.ItemInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLatestItemsUseCase @Inject constructor(val smiteRepository: SmiteRepository) {
  suspend operator fun invoke(): List<ItemInformation> = withContext(Dispatchers.IO) {
    smiteRepository.getItems()
  }
}
