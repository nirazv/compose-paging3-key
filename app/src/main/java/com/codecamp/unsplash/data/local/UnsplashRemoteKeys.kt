package com.codecamp.unsplash.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unsplash_remote_keys_table")
data class UnsplashRemoteKeys(

    @PrimaryKey
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?
)