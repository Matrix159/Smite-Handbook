package com.matrix159.shared.data.usecases

import com.matrix159.shared.data.contracts.SmiteRepository
import com.matrix159.shared.data.models.GodInformation
import kotlinx.coroutines.flow.Flow

class GetLatestGodsUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(): Flow<List<GodInformation>> = smiteRepository.getGods()
}
