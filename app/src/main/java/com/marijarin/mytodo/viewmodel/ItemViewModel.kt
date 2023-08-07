package com.marijarin.mytodo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marijarin.mytodo.dto.Item
import com.marijarin.mytodo.repo.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {
    private val _choice = MutableStateFlow(true)
    val choice: StateFlow<Boolean>
        get() = _choice

    @OptIn(ExperimentalCoroutinesApi::class)
    val data: Flow<List<Item>> = choice.flatMapLatest {
        if (choice.value) {
            repository.dataDesc.map {
                it.map { itemEntity ->
                    itemEntity.toDto()
                }
            }.flowOn(Dispatchers.Default)
        } else {
            repository.dataAsc.map {
                it.map { itemEntity ->
                    itemEntity.toDto()
                }
            }.flowOn(Dispatchers.Default)
        }
    }
 fun changeOrder(order: Boolean){
     _choice.value = order
 }

    fun save(item: Item) {
        viewModelScope.launch {
            try {
                repository.save(item)
            } catch (e: Exception) {
                Log.w(TAG, "Sorting failed", e)
            }
        }
    }


    fun removeById(id: Long) {
        viewModelScope.launch {
            try {
                repository.removeById(id)
            } catch (e: Exception) {
                Log.w(TAG, "Removing failed", e)
            }
        }
    }

    fun removeAll() {
        viewModelScope.launch {
            try {
                repository.clear()
            } catch (e: Exception) {
                Log.w(TAG, "Removing failed", e)
            }
        }
    }

    companion object {
        private const val TAG = "ViewModel"
    }
}