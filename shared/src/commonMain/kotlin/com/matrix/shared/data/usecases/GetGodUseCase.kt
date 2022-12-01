package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.GodInformation
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetGodUseCase: KoinComponent {
  private val smiteRepository by inject<SmiteRepository>()
  operator fun invoke(godId: Int): Flow<GodInformation> = smiteRepository.getGod(godId)
}
