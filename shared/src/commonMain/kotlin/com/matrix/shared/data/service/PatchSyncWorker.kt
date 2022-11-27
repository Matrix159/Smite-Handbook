package com.matrix.shared.data.service

import com.matrix.shared.KmmAppContext
import com.matrix.shared.data.contracts.SmiteRepository

interface IPatchSyncWorker {
  val appContext: KmmAppContext
  val smiteRepository: SmiteRepository
}

//expect class PatchSyncWorker: IPatchSyncWorker{
////  companion object {
////    /**
////     * Expedited one time work to sync data on app startup
////     */
////    fun startUpSyncWork()
////  }
//}



