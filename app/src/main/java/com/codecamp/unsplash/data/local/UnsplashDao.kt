package com.codecamp.unsplash.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UnsplashDao {

    @Upsert
    suspend fun insertAll(unsplashList: List<UnsplashEntity>)

    @Query("SELECT * FROM unsplash_image_table")
    fun getAll() : PagingSource<Int, UnsplashEntity>

    @Query("DELETE FROM unsplash_image_table")
    suspend fun clearAll()

}