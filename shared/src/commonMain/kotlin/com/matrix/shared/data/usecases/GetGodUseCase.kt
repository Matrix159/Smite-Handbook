package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.GodInformation
import kotlinx.coroutines.flow.Flow

class GetGodUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(godId: Int): Flow<GodInformation> = smiteRepository.getGod(godId)
}
