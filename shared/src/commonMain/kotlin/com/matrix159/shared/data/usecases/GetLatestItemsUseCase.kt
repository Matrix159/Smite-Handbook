package com.matrix159.shared.data.usecases

import com.matrix159.shared.data.contracts.SmiteRepository
import com.matrix159.shared.data.models.ItemInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLatestItemsUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(): Flow<List<ItemInformation>> = smiteRepository.getItems().map { items -> items.filter { it.activeFlag } }
}
