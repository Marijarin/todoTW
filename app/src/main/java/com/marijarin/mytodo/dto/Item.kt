package com.marijarin.mytodo.dto

data class Item(
    val id: Long = 0L,
    val title: String,
    val desc: String,
    val date: Long
        )