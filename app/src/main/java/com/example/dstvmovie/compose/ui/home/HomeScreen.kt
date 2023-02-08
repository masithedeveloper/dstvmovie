package com.example.dstvmovie.compose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dstvmovie.R
import com.example.dstvmovie.compose.toMovieDetail
import com.example.dstvmovie.data.constant.Constants
import com.example.dstvmovie.data.model.Item
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: PopularMovieViewModel = hiltViewModel()
) {
    val movieList by viewModel.itemList.observeAsState(listOf())
    val refreshing by viewModel.isRefreshing.observeAsState(false)
    val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.doRefresh() })
    val scrollState = rememberLazyGridState()
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }
    LaunchedEffect(key1 = true, block = {
        viewModel.firstLoad()
    })

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxSize(),
            state = scrollState,
        ) {
            items(
                items = movieList,
                key = { movie: Item -> movie.id.toString() },
                itemContent = { movie ->
                    ItemMovie(
                        movie = movie,
                        onClick = {
                            navController.toMovieDetail(movieId = it.id.toString())
                        },
                    )
                }
            )
        }
        


        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

    LaunchedEffect(key1 = endOfListReached, block = {
        viewModel.doLoadMore()
    })
}

@Composable
fun BottomNavigationBar(viewModel: PopularMovieViewModel = hiltViewModel()) {
    BottomNavigation(
        backgroundColor = Color(0xFF0F9D58)) {

        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = false,
                onClick = {
                    viewModel.valueToOrderBy.value = navItem.valueToOrderBy
                    viewModel.filterByRank()
                },
                icon = {},
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}

fun LazyGridState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 5

@Composable
fun ItemMovie(movie: Item, onClick: (Item) -> Unit) {
    Box(
        modifier = Modifier
            .clickable {
                onClick.invoke(movie)
            }
            .wrapContentHeight()
    ) {
        GlideImage(
            imageModel = { movie.getImagePath() },
            modifier = Modifier.size(200.dp),
            component = rememberImageComponent {
                +PlaceholderPlugin.Loading(Icons.Filled.Image)
                +PlaceholderPlugin.Failure(Icons.Filled.Error)
            },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
            ),
        )

        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.black_50))
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = movie.title ?: "",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}