package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.BuildInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildsUseCase @Inject constructor(private val smiteRepository: SmiteRepository) {
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