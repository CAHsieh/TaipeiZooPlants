package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.source.local.IExhibitDatasource
import ca.pet.taipeizooplants.data.source.local.IExhibitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ExhibitRepository(
//        private val exhibitRemoteDataSource: IExhibitDatasource,
        private val exhibitLocalDataSource: IExhibitDatasource,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IExhibitRepository {


}