package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodSkinInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGodSkinsUseCase @Inject constructor(private val smiteRepository: SmiteRepository) {
  operator fun invoke(godId: Int): Flow<List<GodSkinInformation>> =
    smiteRepository.getGodSkins(godId)
}
