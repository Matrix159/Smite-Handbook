package com.matrix159.shared.data.usecases

import com.matrix159.shared.data.contracts.SmiteRepository
import com.matrix159.shared.data.models.GodSkinInformation
import kotlinx.coroutines.flow.Flow

class GetGodSkinsUseCase constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(godId: Int): Flow<List<GodSkinInformation>> =
    smiteRepository.getGodSkins(godId)
}
