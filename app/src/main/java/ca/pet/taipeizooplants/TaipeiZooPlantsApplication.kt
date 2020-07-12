package ca.pet.taipeizooplants

import android.app.Application
import ca.pet.taipeizooplants.data.source.local.IExhibitRepository
import ca.pet.taipeizooplants.data.source.local.IPlantsRepository

class TaipeiZooPlantsApplication : Application() {

    val plantsRepository: IPlantsRepository
        get() = ServiceLocator.providePlantsRepository(this)

    val exhibitsRepository: IExhibitRepository
        get() = ServiceLocator.provideExhibitRepository(this)
}