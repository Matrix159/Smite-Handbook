package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetGodSkinsUseCase: KoinComponent {
  private val smiteRepository by inject<SmiteRepository>()
  operator fun invoke(godId: Long): Flow<List<GodSkinInformation>> =
    smiteRepository.getGodSkins(godId)
}
