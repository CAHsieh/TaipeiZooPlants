package ca.pet.taipeizooplants

import android.app.Application
import android.content.Context
import ca.pet.taipeizooplants.data.source.IExhibitRepository
import ca.pet.taipeizooplants.data.source.IPlantsRepository

class TaipeiZooPlantsApplication : Application() {

    val plantsRepository: IPlantsRepository
        get() = ServiceLocator.providePlantsRepository(this)

    val exhibitsRepository: IExhibitRepository
        get() = ServiceLocator.provideExhibitRepository(this)

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: Context? = null
    }
}