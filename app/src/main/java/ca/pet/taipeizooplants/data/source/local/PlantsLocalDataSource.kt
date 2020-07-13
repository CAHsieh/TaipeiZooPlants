package ca.pet.taipeizooplants.data.source.local

import ca.pet.taipeizooplants.data.Plants
import ca.pet.taipeizooplants.data.source.IPlantsDataSource
import kotlinx.coroutines.*

class PlantsLocalDataSource internal constructor(
    private val plantsDao: PlantsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPlantsDataSource {

    override suspend fun getDataCount(location: String): Int? {
        val job = GlobalScope.async {
            withTimeoutOrNull(DB_QUERY_TIMEOUT) {
                plantsDao.getDataCount(location)
            }
        }
        return job.await()
    }

    override suspend fun retrievePlants(
        location: String,
        callback: (List<Plants>?) -> Unit
    ) {
        val databaseJob = GlobalScope.async {
            withTimeoutOrNull(DB_QUERY_TIMEOUT) {
                plantsDao.loadPlants(location)
            }
        }
        callback(databaseJob.await())
    }


}