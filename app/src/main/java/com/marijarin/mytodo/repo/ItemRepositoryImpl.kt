package com.marijarin.mytodo.repo

import android.util.Log
import com.marijarin.mytodo.dao.ItemDao
import com.marijarin.mytodo.dto.Item
import com.marijarin.mytodo.entity.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val dao: ItemDao
) : ItemRepository {
    override val dataDesc: Flow<List<ItemEntity>>
        get() = dao.getAllItemsDesc().flowOn(Dispatchers.Default)

    override val dataAsc: Flow<List<ItemEntity>>
        get() = dao.getAllItemsAsc().flowOn(Dispatchers.Default)

    override suspend fun save(item: Item) {
        try {
            dao.insert(ItemEntity.fromDto(item))
        } catch (e: Exception) {
            Log.w(TAG, "Saving failed", e)
        }
    }

    override suspend fun removeById(id: Long) {
        try {
            dao.removeById(id)
        } catch (e: Exception) {
            Log.w(TAG, "Removing failed", e)
        }
    }

    override suspend fun clear() {
        try {
            dao.removeAll()
        } catch (e: Exception) {
            Log.w(TAG, "Clearing failed", e)
        }
    }

    companion object {
        private const val TAG = "Repository"
    }
}