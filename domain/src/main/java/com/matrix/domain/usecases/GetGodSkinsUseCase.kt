package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodSkin
import javax.inject.Inject

class GetGodSkinsUseCase @Inject constructor(val smiteRepository: SmiteRepository) {
  suspend operator fun invoke(godId: Int): List<GodSkin> {
    return smiteRepository.getGodSkins(godId)
  }
}
