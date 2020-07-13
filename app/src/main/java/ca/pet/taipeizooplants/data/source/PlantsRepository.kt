package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.Plants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PlantsRepository(
    private val plantsRemoteDataSource: IPlantsDataSource,
    private val plantsLocalDataSource: IPlantsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPlantsRepository {

    override suspend fun retrievePlants(location: String, callback: (List<Plants>) -> Unit) {
        val localCount = plantsLocalDataSource.getDataCount("%${location}%")
        val remoteCount = plantsRemoteDataSource.getDataCount(location)
        if (localCount != remoteCount) {
            plantsRemoteDataSource.retrievePlants(location) {
                it?.let { plants -> callback(plants) }
            }
        } else {
            plantsLocalDataSource.retrievePlants("%${location}%") {
                it?.let { plants ->
                    callback(plants)
                }
            }
        }
    }

}