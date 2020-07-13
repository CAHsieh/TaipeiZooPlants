package ca.pet.taipeizooplants.data.source

import ca.pet.taipeizooplants.data.Exhibit

interface IExhibitRepository {

    suspend fun retrieveExhibits(callback: (List<Exhibit>) -> Unit)

    suspend fun retrieveMoreExhibits(callback: (List<Exhibit>) -> Unit)
}