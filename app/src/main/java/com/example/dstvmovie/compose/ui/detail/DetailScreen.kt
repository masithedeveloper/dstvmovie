package com.example.dstvmovie.compose.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dstvmovie.R
import com.example.dstvmovie.compose.ui.widget.BoxContent
import com.example.dstvmovie.data.model.Item
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

@Composable
fun DetailScreen(
    navController: NavController,
    itemId: String?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val item by viewModel.item.collectAsState()
    LaunchedEffect(key1 = itemId, block = {
        viewModel.setValueMovieId(id = itemId)
        viewModel.getMovieDetail(itemId = itemId)
    })

    BoxContent(
        enableRefresh = true,
        viewModel = viewModel
    ) {
        item?.let {
            MovieDetailBody(
                item = it,
                onClickBack = { navController.popBackStack() },
            )
        } ?: MovieDetailEmptyBody(onClickBack = {
            navController.popBackStack()
        })
    }
}

@Composable
fun MovieDetailBody(
    item: Item,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                imageModel = { item.getImagePath()},
                modifier = Modifier.fillMaxWidth(),
                component = rememberImageComponent {
                    +PlaceholderPlugin.Loading(Icons.Filled.Image)
                    +PlaceholderPlugin.Failure(Icons.Filled.Error)
                },
                imageOptions = ImageOptions(),
            )
            Image(
                painterResource(R.drawable.ic_arrow_back_white_24dp),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClickBack.invoke()
                    }
                    .padding(12.dp),
            )
        }
        Text(
            item.title ?: "",
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
        )
        Text(item.rank.toString(), color = Color.White)
        Text(item.synopsis ?: "", color = Color.White)
    }
}

@Composable
fun MovieDetailEmptyBody(
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painterResource(R.drawable.ic_arrow_back_white_24dp),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClickBack.invoke()
                    }
                    .padding(12.dp),
            )
        }
    }
}