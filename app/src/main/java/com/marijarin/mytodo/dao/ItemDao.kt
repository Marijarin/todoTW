package com.marijarin.mytodo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marijarin.mytodo.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemEntity ORDER BY id DESC")
    fun getAllItemsDesc(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM ItemEntity ORDER BY id ASC")
    fun getAllItemsAsc(): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<ItemEntity>)

    @Query("DELETE FROM ItemEntity WHERE id = :id")
    suspend fun removeById(id: Long)

    @Query("DELETE FROM ItemEntity")
    suspend fun removeAll()
}