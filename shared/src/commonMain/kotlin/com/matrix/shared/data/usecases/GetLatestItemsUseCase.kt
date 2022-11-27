package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.ItemInformation
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLatestItemsUseCase constructor(private val smiteRepository: SmiteRepository = OfflineFirstSmiteRepository()) {
  operator fun invoke(): Flow<List<ItemInformation>> = smiteRepository.getItems().map { items -> items.filter { it.activeFlag } }
}
