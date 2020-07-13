package ca.pet.taipeizooplants.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.data.Plants

@Database(entities = [Exhibit::class, Plants::class], version = 1, exportSchema = false)
abstract class TaipeiZooPlantsDatabase : RoomDatabase() {
    abstract fun exhibitDao(): ExhibitDao
    abstract fun plantsDao(): PlantsDao
}

const val DB_QUERY_TIMEOUT = 3000L