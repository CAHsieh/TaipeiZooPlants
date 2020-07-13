package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.Exhibit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class ExhibitRepository(
    private val exhibitRemoteDataSource: IExhibitDataSource,
    private val exhibitLocalDataSource: IExhibitDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IExhibitRepository {

    private var dataSize = 10
    private var isLimited = false

    override suspend fun retrieveExhibits(callback: (List<Exhibit>) -> Unit) {

        val count = exhibitLocalDataSource.getDataCount()
        if (count == 0) {
            exhibitRemoteDataSource.retrieveExhibits(exhibitRemoteDataSource.getDataCount()!!) {
                it?.let { exhibits ->
                    callback(
                        if (exhibits.size > dataSize) {
                            exhibits.subList(0, dataSize)
                        } else {
                            exhibits
                        }
                    )
                }
            }
        } else {
            retrieveData(callback)
        }
    }

    override suspend fun retrieveMoreExhibits(callback: (List<Exhibit>) -> Unit) {

        if (!isLimited) {
            dataSize *= 2
        }

        retrieveData(callback)
    }


    private suspend fun retrieveData(callback: (List<Exhibit>) -> Unit) {
        exhibitLocalDataSource.retrieveExhibits(dataSize) {
            it?.let { exhibits ->
                isLimited = (exhibits.size < dataSize)
                callback(exhibits)
            }
        }
    }
}