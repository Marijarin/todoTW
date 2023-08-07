package com.marijarin.mytodo.repo

import com.marijarin.mytodo.dto.Item
import com.marijarin.mytodo.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    val dataDesc: Flow<List<ItemEntity>>
    val dataAsc: Flow<List<ItemEntity>>
    suspend fun save(item: Item)
    suspend fun removeById(id: Long)
    suspend fun clear()

}