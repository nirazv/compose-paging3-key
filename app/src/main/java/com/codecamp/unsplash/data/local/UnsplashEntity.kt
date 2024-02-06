package com.codecamp.unsplash.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unsplash_image_table")
data class UnsplashEntity(

    @PrimaryKey
    val id: String,
    @Embedded
    val urls: Urls,
    val likes: Int,
    @Embedded
    val user: User
)

data class Urls(
    val regular: String
)

data class User(
    val name: String,
    @Embedded
    val links: UserLinks
)

data class UserLinks(
    val html: String
)



