package com.codecamp.unsplash.domain.usecase


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.codecamp.unsplash.data.local.UnsplashDatabase
import com.codecamp.unsplash.data.local.UnsplashEntity
import com.codecamp.unsplash.data.mapper.toDomainModel
import com.codecamp.unsplash.data.paging.UnsplashRemoteMediator
import com.codecamp.unsplash.data.remote.UnsplashApi
import com.codecamp.unsplash.domain.model.UnsplashImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UnsplashPagedUseCase @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    operator fun invoke() : Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = { unsplashDatabase.unsplashDao.getAll() }
        ).flow.map {pagingData: PagingData<UnsplashEntity> ->
            pagingData.map { it.toDomainModel() }
        }
    }
}