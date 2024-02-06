package com.codecamp.unsplash.data.mapper

import com.codecamp.unsplash.data.local.UnsplashEntity
import com.codecamp.unsplash.data.local.Urls
import com.codecamp.unsplash.data.local.User
import com.codecamp.unsplash.data.local.UserLinks
import com.codecamp.unsplash.data.remote.UnsplashDto
import com.codecamp.unsplash.domain.model.UnsplashImage

fun UnsplashDto.toEntityModel(): UnsplashEntity {
    return UnsplashEntity(
        id = this.id,
        urls = Urls(this.urls.regular),
        likes = this.likes,
        user = User(
            name = this.user.name,
            links = UserLinks(this.user.links.html)
        )
    )
}

fun UnsplashEntity.toDomainModel() : UnsplashImage {
    return UnsplashImage(
        id = this.id,
        urls = UnsplashImage.Urls(this.urls.regular),
        likes = this.likes,
        user = UnsplashImage.User(
            name = this.user.name,
            links = UnsplashImage.User.UserLinks(this.user.links.html)
        )
    )
}
