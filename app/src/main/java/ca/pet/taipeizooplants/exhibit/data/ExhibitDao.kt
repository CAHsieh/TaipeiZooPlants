package ca.pet.taipeizooplants.exhibit.data

import androidx.room.*

@Dao
public interface ExhibitDao {

    //查詢全部資料
    @Query("Select * from exhibit")
    fun getAll(): List<Exhibit>

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