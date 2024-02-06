package com.codecamp.unsplash.domain.model


data class UnsplashImage(
    val id: String,
    val urls: Urls,
    val likes: Int,
    val user: User
) {
    data class Urls(
        val regular: String
    )

    data class User(
        val name: String,
        val links: UserLinks
    ) {
        data class UserLinks(
            val html: String
        )
    }
}
