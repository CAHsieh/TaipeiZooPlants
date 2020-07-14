package ca.pet.taipeizooplants.data.source.local

import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.data.source.IExhibitDataSource
import kotlinx.coroutines.*

class ExhibitLocalDataSource internal constructor(
    private val exhibitDao: ExhibitDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IExhibitDataSource {

    override suspend fun getDataCount(): Int? {
        val job = GlobalScope.async {
            withTimeoutOrNull(DB_QUERY_TIMEOUT) {
                exhibitDao.getDataCount()
            }
        }
        return job.await()
    }

    override suspend fun retrieveExhibits(limit: Int, callback: (List<Exhibit>?) -> Unit) {

        val databaseJob = GlobalScope.async {
            withTimeoutOrNull(DB_QUERY_TIMEOUT) {
                exhibitDao.loadExhibits(limit, 0)
            }
        }
        callback(databaseJob.await())
    }
}