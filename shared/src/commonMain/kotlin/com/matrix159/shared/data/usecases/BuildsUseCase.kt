package com.matrix159.shared.data.usecases

import com.matrix159.shared.data.repository.OfflineFirstSmiteRepository
import com.matrix159.shared.data.contracts.SmiteRepository
import com.matrix159.shared.data.models.BuildInformation
import kotlinx.coroutines.flow.Flow

class BuildsUseCase constructor(private val smiteRepository: SmiteRepository = OfflineFirstSmiteRepository()) {
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