package com.marijarin.mytodo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marijarin.mytodo.dao.ItemDao
import com.marijarin.mytodo.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)

abstract class AppDb: RoomDatabase() {
    abstract fun itemDao(): ItemDao
}