package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.GodSkinInformation
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import kotlinx.coroutines.flow.Flow

class GetGodSkinsUseCase constructor(private val smiteRepository: SmiteRepository = OfflineFirstSmiteRepository()) {
  operator fun invoke(godId: Int): Flow<List<GodSkinInformation>> =
    smiteRepository.getGodSkins(godId)
}
