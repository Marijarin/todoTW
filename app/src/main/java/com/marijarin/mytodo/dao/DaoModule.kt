package com.marijarin.mytodo.dao

import com.marijarin.mytodo.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    fun provideDishDao(db: AppDb): ItemDao = db.itemDao()

}