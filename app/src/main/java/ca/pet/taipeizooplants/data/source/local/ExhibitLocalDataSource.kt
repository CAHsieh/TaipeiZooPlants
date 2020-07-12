package ca.pet.taipeizooplants.data.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ExhibitLocalDataSource internal constructor(
        private val exhibitDao: ExhibitDao,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IExhibitDatasource {
}