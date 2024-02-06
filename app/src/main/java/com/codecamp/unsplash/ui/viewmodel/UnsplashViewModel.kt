package com.codecamp.unsplash.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codecamp.unsplash.domain.model.UnsplashImage
import com.codecamp.unsplash.domain.usecase.UnsplashPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(
    private val useCase: UnsplashPagedUseCase
) : ViewModel() {
    val unsplashImagesFlow = useCase().cachedIn(viewModelScope)
}