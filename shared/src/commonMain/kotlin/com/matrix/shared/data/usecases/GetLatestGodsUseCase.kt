package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.GodInformation
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import kotlinx.coroutines.flow.Flow

class GetLatestGodsUseCase constructor(private val smiteRepository: SmiteRepository = OfflineFirstSmiteRepository()) {
  operator fun invoke(): Flow<List<GodInformation>> = smiteRepository.getGods()
}
