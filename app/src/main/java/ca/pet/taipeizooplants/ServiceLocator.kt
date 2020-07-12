package ca.pet.taipeizooplants

import android.content.Context
import androidx.room.Room
import ca.pet.taipeizooplants.data.source.ExhibitRepository
import ca.pet.taipeizooplants.data.source.PlantsRepository
import ca.pet.taipeizooplants.data.source.local.*

object ServiceLocator {

    private var database: TaipeiZooPlantsDatabase? = null

    @Volatile
    private var plantsRepository: IPlantsRepository? = null

    @Volatile
    private var exhibitRepository: IExhibitRepository? = null

    fun providePlantsRepository(context: Context): IPlantsRepository {
        synchronized(this) {
            return plantsRepository ?: createPlantsRepository(context)
        }
    }

    fun provideExhibitRepository(context: Context): IExhibitRepository {
        synchronized(this) {
            return exhibitRepository ?: createExhibitRepository(context)
        }
    }

    private fun createPlantsRepository(context: Context): IPlantsRepository {
        val newRepo = PlantsRepository(createPlantsLocalDataSource(context))
        plantsRepository = newRepo
        return newRepo
    }

    private fun createExhibitRepository(context: Context): IExhibitRepository {
        val newRepo = ExhibitRepository(createExhibitLocalDataSource(context))
        exhibitRepository = newRepo
        return newRepo
    }

    private fun createPlantsLocalDataSource(context: Context): IPlantsDatasource {
        val database = database ?: createDatabase(context)
        return PlantsLocalDataSource(database.plantsDao())
    }

    private fun createExhibitLocalDataSource(context: Context): IExhibitDatasource {
        val database = database ?: createDatabase(context)
        return ExhibitLocalDataSource(database.exhibitDao())
    }


    private fun createDatabase(context: Context): TaipeiZooPlantsDatabase {
        val result = Room.databaseBuilder(
                context.applicationContext,
                TaipeiZooPlantsDatabase::class.java,
                "TaipeiZooPlants.db"
        ).build()
        database = result
        return result
    }
}