package com.matrix.shared.data.usecases

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.models.BuildInformation
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
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