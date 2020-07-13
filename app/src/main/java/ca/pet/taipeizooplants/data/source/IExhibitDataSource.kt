package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.Exhibit

interface IExhibitDataSource {

    suspend fun retrieveExhibits(limit: Int, callback: (List<Exhibit>?) -> Unit)

    suspend fun getDataCount():Int?
}