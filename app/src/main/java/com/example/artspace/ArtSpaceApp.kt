package com.example.artspace

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    val artSpaceObjects = remember {
        mutableStateListOf(*artSpaceObjectsInit)
    }

    val pagerState = rememberPagerState(pageCount = {
        artSpaceObjects.size
    })

    ArtSpaceTheme {
        Scaffold(
            modifier = modifier.fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(it)
            ) { page ->
                Column {
                    Image(
                        painter = painterResource(id = artSpaceObjects[page].image),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(32.dp)
                            .shadow(elevation = 8.dp)
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(32.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 32.dp, end = 32.dp)
                            .fillMaxWidth()
                            .background(color = Color(0x77C5C5C5))
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    ) {
                        Text(
                            text = artSpaceObjects[page].title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = artSpaceObjects[page].description,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = artSpaceObjects[page].author
                                        + " (${artSpaceObjects[page].year})",
                                style = MaterialTheme.typography.titleSmall
                            )
                            IconButton(
                                onClick = {
                                    val updatedObject = artSpaceObjects[page]
                                        .copy(like = !artSpaceObjects[page].like)
                                    artSpaceObjects[page] = updatedObject
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    tint = if (artSpaceObjects[page].like)
                                        Color(0xFFFF0000)
                                    else
                                        Color(0x34000000),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}