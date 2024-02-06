package com.codecamp.unsplash.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.codecamp.unsplash.ui.viewmodel.UnsplashViewModel

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { HomeTopBar({}) },
        content = { paddingValues -> HomeContent(paddingValues) }
    )
}


@Composable
fun HomeContent(paddingValues: PaddingValues, viewModel: UnsplashViewModel = hiltViewModel()) {
    val unsplashImages = viewModel.unsplashImagesFlow.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = {

//            item {
//
//            }

            items(
                unsplashImages.itemCount
            ) {
                unsplashImages[it]?.let { it1 -> HomeItem(image = it1) }
            }

//            item {
//
//            }
        })
}


