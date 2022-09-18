package com.matrix.domain.usecases

import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLatestGodsUseCase @Inject constructor(val smiteRepository: SmiteRepository) {
  suspend operator fun invoke(): List<GodInformation> = withContext(Dispatchers.IO) {
    smiteRepository.getGods()
  }
}
