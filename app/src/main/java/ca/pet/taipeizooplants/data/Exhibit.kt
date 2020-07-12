package ca.pet.taipeizooplants.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exhibit(
        @PrimaryKey
        val id: Int,
        val E_Category: String,
        val E_Geo: String,
        val E_Info: String,
        val E_Memo: String,
        val E_Name: String,
        val E_Pic_URL: String,
        val E_URL: String,
        val E_no: Int
)