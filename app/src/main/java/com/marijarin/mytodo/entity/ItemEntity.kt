package com.marijarin.mytodo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijarin.mytodo.dto.Item

@Entity
data class ItemEntity (
    @PrimaryKey (autoGenerate = true)
    val id: Long,
    val title: String,
    val desc: String,
    val date: Long
        ) {
    fun toDto() = Item(
        id,
        title,
        desc,
        date
    )

    companion object {
        fun fromDto(dto: Item) = ItemEntity(
            dto.id,
            dto.title,
            dto.desc,
            dto.date
        )
    }
}

