package ca.pet.taipeizooplants

import android.content.Context
import androidx.room.Room
import ca.pet.taipeizooplants.data.source.*
import ca.pet.taipeizooplants.data.source.local.ExhibitLocalDataSource
import ca.pet.taipeizooplants.data.source.local.PlantsLocalDataSource
import ca.pet.taipeizooplants.data.source.local.TaipeiZooPlantsDatabase
import ca.pet.taipeizooplants.data.source.remote.FakeExhibitRemoteDataSource

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
        val newRepo = ExhibitRepository(
            createFakeExhibitRemoteDataSource(context),
            createExhibitLocalDataSource(context)
        )
        exhibitRepository = newRepo
        return newRepo
    }

    private fun createPlantsLocalDataSource(context: Context): IPlantsDataSource {
        val database = database ?: createDatabase(context)
        return PlantsLocalDataSource(database.plantsDao())
    }

    private fun createExhibitLocalDataSource(context: Context): IExhibitDataSource {
        val database = database ?: createDatabase(context)
        return ExhibitLocalDataSource(database.exhibitDao())
    }

    private fun createFakeExhibitRemoteDataSource(context: Context): IExhibitDataSource {
        val database = database ?: createDatabase(context)
        return FakeExhibitRemoteDataSource(database.exhibitDao())
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