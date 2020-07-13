package ca.pet.taipeizooplants.data.source.remote

import ca.pet.taipeizooplants.TaipeiZooPlantsApplication
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.data.source.IExhibitDataSource
import ca.pet.taipeizooplants.data.source.local.ExhibitDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.io.BufferedInputStream
import java.io.InputStreamReader

class FakeExhibitRemoteDataSource internal constructor(
    private val exhibitDao: ExhibitDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IExhibitDataSource {

    private val list: List<Exhibit>

    init {
        val inputStream = TaipeiZooPlantsApplication.context?.assets?.open("data_exhibit.json")
        val bufferedInputStream = BufferedInputStream(inputStream)
        val gson = Gson()
        val jsonReader = JsonReader(InputStreamReader(bufferedInputStream))
        val tokenType = object : TypeToken<List<Exhibit>>() {}.type
        list = gson.fromJson<List<Exhibit>>(jsonReader, tokenType)
    }

    override suspend fun retrieveExhibits(limit: Int, callback: (List<Exhibit>?) -> Unit) {

        if (limit >= list.size) {
            exhibitDao.insertAll(list)
            callback(list)
        } else {
            callback(list.subList(0, limit))
        }
    }

    override suspend fun getDataCount(): Int? {

        return list.size
    }
}