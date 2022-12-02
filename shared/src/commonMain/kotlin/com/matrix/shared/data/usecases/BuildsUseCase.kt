package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.builds.BuildInformation
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BuildsUseCase: KoinComponent {
  private val smiteRepository by inject<SmiteRepository>()
  fun getBuilds(): Flow<List<BuildInformation>> {
    return smiteRepository.getBuilds()
  }

  fun getBuild(buildId: Int): Flow<BuildInformation> {
    return smiteRepository.getBuild(buildId)
  }

  suspend fun createBuild(buildInformation: BuildInformation) {
    smiteRepository.createBuild(buildInformation)
  }

  suspend fun deleteBuild(buildInformation: BuildInformation) {
    smiteRepository.deleteBuild(buildInformation)
  }
}