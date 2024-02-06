package com.codecamp.unsplash.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_keys_table WHERE id=:key")
    suspend fun getRemoteKey(key:String) : UnsplashRemoteKeys

    @Query("DELETE FROM unsplash_remote_keys_table")
    suspend fun clearAllRemoteKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(keys: List<UnsplashRemoteKeys>)
}