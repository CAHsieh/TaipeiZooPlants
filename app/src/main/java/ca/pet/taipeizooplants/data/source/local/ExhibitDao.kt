package ca.pet.taipeizooplants.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.pet.taipeizooplants.data.Exhibit

@Dao
public interface ExhibitDao {

    @Query("Select Count(id) from exhibit")
    fun getDataCount(): Int

    @Query("Select * from exhibit limit :limit offset :offset")
    fun loadExhibits(limit: Int, offset: Int): List<Exhibit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(exhibit: List<Exhibit>)
}