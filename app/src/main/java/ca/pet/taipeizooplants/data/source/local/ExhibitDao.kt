package ca.pet.taipeizooplants.data.source.local

import androidx.room.*
import ca.pet.taipeizooplants.data.Exhibit

@Dao
public interface ExhibitDao {

    //查詢全部資料
    @Query("Select * from exhibit")
    fun getAll(): List<Exhibit>

    @Query("Select * from exhibit limit :limit offset :offset")
    fun loadExhibits(limit: Int, offset: Int): List<Exhibit>

    //刪除全部資料
    @Query("Delete from exhibit")
    fun deleteAll()

    @Insert
    fun insert(vararg exhibit: Exhibit)

    @Delete
    fun delete(vararg exhibit: Exhibit)

    @Update
    fun update(vararg exhibit: Exhibit)

}