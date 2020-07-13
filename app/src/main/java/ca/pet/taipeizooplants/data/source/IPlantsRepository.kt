package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.Plants


interface IPlantsRepository {

    suspend fun retrievePlants(location: String, callback: (List<Plants>) -> Unit)
}