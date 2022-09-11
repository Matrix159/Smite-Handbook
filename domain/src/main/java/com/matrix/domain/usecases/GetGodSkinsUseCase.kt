package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodSkinInformation
import javax.inject.Inject

class GetGodSkinsUseCase @Inject constructor(val smiteRepository: SmiteRepository) {
  suspend operator fun invoke(godId: Int): List<GodSkinInformation> {
    return smiteRepository.getGodSkins(godId)
  }
}
