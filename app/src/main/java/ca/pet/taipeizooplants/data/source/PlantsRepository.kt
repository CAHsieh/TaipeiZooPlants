package ca.pet.taipeizooplants.data.source

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PlantsRepository(
//        private val plantsRemoteDataSource : IPlantsDatasource,
        private val plantsLocalDataSource: IPlantsDataSource,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPlantsRepository {


}