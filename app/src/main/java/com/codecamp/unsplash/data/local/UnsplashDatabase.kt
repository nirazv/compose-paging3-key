package com.codecamp.unsplash.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UnsplashEntity::class,UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract val unsplashDao: UnsplashDao
    abstract val unsplashRemoteKeysDao: UnsplashRemoteKeysDao
}