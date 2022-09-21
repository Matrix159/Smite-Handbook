package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGodUseCase @Inject constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(godId: Int): Flow<GodInformation> = smiteRepository.getGod(godId)
}
