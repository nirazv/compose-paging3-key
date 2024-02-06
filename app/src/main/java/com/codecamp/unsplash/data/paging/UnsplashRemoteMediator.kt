package com.codecamp.unsplash.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codecamp.unsplash.data.local.UnsplashDatabase
import com.codecamp.unsplash.data.local.UnsplashEntity
import com.codecamp.unsplash.data.local.UnsplashRemoteKeys
import com.codecamp.unsplash.data.mapper.toEntityModel
import com.codecamp.unsplash.data.remote.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashEntity>() {

    private val unsplashDao = unsplashDatabase.unsplashDao
    private val unsplashRemoteKeysDao = unsplashDatabase.unsplashRemoteKeysDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashEntity>
    ): MediatorResult {
        return try {
            val loadPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val response = unsplashApi.getUnsplashImageResult(page = loadPage, pageSize = 20)

            val endOfPaginationResponse = response.isEmpty()
            val prevPage = if(loadPage == 1) null else loadPage-1
            val nextPage = if(endOfPaginationResponse) null else loadPage+1


            unsplashDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    unsplashDao.clearAll()
                    unsplashRemoteKeysDao.clearAllRemoteKeys()
                }

                val keys = response.map {unsplash ->
                    UnsplashRemoteKeys(
                        id = unsplash.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                unsplashRemoteKeysDao.addAllRemoteKeys(keys = keys)
                val entities = response.map { it.toEntityModel() }
                unsplashDao.insertAll(entities)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationResponse)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashEntity>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()

        }?.data?.firstOrNull()?.let { unsplashEntity ->
            unsplashRemoteKeysDao.getRemoteKey(unsplashEntity.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashEntity>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { unsplashEntity ->
            unsplashRemoteKeysDao.getRemoteKey(unsplashEntity.id)
        }
    } // end of getRemoteKeyForLastItem

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UnsplashEntity>): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKey(id)
            }
        }
    } //end of getRemoteKeyClosestToCurrentPosition
}