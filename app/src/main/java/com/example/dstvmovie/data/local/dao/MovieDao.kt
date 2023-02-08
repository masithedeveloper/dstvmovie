package com.example.dstvmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.dstvmovie.data.model.Item

@Dao
interface MovieDao {

    @Query("SELECT * FROM item WHERE item.id = :id")
    suspend fun getItem(id: String): Item?

    @Query("SELECT * FROM item")
    suspend fun getItemList(): List<Item>?

    @Insert(onConflict = IGNORE)
    suspend fun insert(list: List<Item>)

    @Query("DELETE FROM item")
    suspend fun deleteAll()
//    @Query("SELECT * FROM item ORDER BY item.releaseDate = :valueToOrderBy ASC")
//    suspend fun getItemsByOrder(valueToOrderBy: String?) : List<Item>?
    @Query("SELECT * FROM item ORDER BY " +
        "CASE WHEN :filterByRank = 1 THEN item.rank  END ASC, " +
                "CASE WHEN :filterByRank = 2 THEN item.releaseDate END DESC ")
    suspend fun getItemsByOrder(filterByRank:  Int) : List<Item>?

}