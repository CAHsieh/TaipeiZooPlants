package ca.pet.taipeizooplants.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.pet.taipeizooplants.data.Plants

@Dao
public interface PlantsDao {

    @Query("Select Count(id) from plants where F_Location like :location")
    fun getDataCount(location: String): Int

    @Query("Select * from plants where F_Location like :location")
    fun loadPlants(location: String): List<Plants>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Plants>)

}