package ca.pet.taipeizooplants.data.source.remote

import ca.pet.taipeizooplants.TaipeiZooPlantsApplication
import ca.pet.taipeizooplants.data.Plants
import ca.pet.taipeizooplants.data.source.IPlantsDataSource
import ca.pet.taipeizooplants.data.source.local.PlantsDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.io.BufferedInputStream
import java.io.InputStreamReader

class FakePlantsRemoteDataSource internal constructor(
    private val plantsDao: PlantsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPlantsDataSource {

    private val list: List<Plants>

    init {
        val inputStream = TaipeiZooPlantsApplication.context?.assets?.open("data_plants.json")
        val bufferedInputStream = BufferedInputStream(inputStream)
        val gson = Gson()
        val jsonReader = JsonReader(InputStreamReader(bufferedInputStream))
        val tokenType = object : TypeToken<List<Plants>>() {}.type
        list = gson.fromJson<List<Plants>>(jsonReader, tokenType)
    }

    override suspend fun retrievePlants(
        location: String,
        callback: (List<Plants>?) -> Unit
    ) {

        val filterList = list.filter { it.F_Location.contains(location) }

        plantsDao.insertAll(filterList)
        delay(500L) //給網路一點時間
        callback(filterList)
    }

    override suspend fun getDataCount(location: String): Int? {
        val filterList = list.filter { it.F_Location.contains(location) }
        delay(500L) //給網路一點時間
        return filterList.size
    }
}