package com.matrix159.shared.data.usecases

import com.matrix159.shared.data.contracts.SmiteRepository
import com.matrix159.shared.data.models.GodInformation
import kotlinx.coroutines.flow.Flow

class GetGodUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(godId: Int): Flow<GodInformation> = smiteRepository.getGod(godId)
}
