package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.Plants

interface IPlantsDataSource {

    suspend fun retrievePlants(location: String, callback: (List<Plants>?) -> Unit)

    suspend fun getDataCount(location: String): Int?

}