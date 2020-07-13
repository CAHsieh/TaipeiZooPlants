package ca.pet.taipeizooplants.data.source.local

import ca.pet.taipeizooplants.data.source.IPlantsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PlantsLocalDataSource internal constructor(
    private val plantsDao: PlantsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPlantsDataSource {
}