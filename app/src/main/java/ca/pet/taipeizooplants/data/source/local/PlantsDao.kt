package ca.pet.taipeizooplants.data.source.local

import androidx.room.*
import ca.pet.taipeizooplants.data.Plants

@Dao
public interface PlantsDao {

    //查詢全部資料
    @Query("Select * from Plants")
    fun getAll(): List<Plants>

    @Query("Select * from Plants Where F_Location like :location limit :limit offset :offset")
    fun getPlantsByLocation(location: String, limit: Int, offset: Int): List<Plants>

    //刪除全部資料
    @Query("Delete from Plants")
    fun deleteAll()

    @Insert
    fun insert(vararg Plants: Plants)

    @Delete
    fun delete(vararg Plants: Plants)

    @Update
    fun update(vararg Plants: Plants)

}