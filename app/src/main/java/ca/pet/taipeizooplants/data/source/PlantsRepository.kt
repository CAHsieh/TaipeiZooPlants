package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.source.local.IPlantsDatasource
import ca.pet.taipeizooplants.data.source.local.IPlantsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PlantsRepository(
//        private val plantsRemoteDataSource : IPlantsDatasource,
        private val plantsLocalDataSource: IPlantsDatasource,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPlantsRepository {


}