package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestGodsUseCase @Inject constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(): Flow<List<GodInformation>> = smiteRepository.getGods()
}
