package com.example.artspace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme


@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    var artSpaceObjectsIndex by remember {
        mutableIntStateOf(0)
    }

    var artSpaceObjects = remember {
        mutableStateListOf(*artSpaceObjectsInit)
    }

    ArtSpaceTheme {
        Scaffold(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(id = artSpaceObjects[artSpaceObjectsIndex].image),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                        .fillMaxWidth()
                        .shadow(elevation = 8.dp)
                        .background(Color.White)
                        .padding(32.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0x77C5C5C5))
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                ) {
                    Text(
                        text = artSpaceObjects[artSpaceObjectsIndex].title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = artSpaceObjects[artSpaceObjectsIndex].description,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = artSpaceObjects[artSpaceObjectsIndex].author
                                + " (${artSpaceObjects[artSpaceObjectsIndex].year})",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            artSpaceObjectsIndex = (artSpaceObjects.size + artSpaceObjectsIndex - 1) % artSpaceObjects.size                        }
                    ) {
                        Text(text = "Назад")
                    }
                    IconButton(
                        onClick = {
                            val updatedObject = artSpaceObjects[artSpaceObjectsIndex].copy(like = !artSpaceObjects[artSpaceObjectsIndex].like)
                            artSpaceObjects[artSpaceObjectsIndex] = updatedObject
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            tint = if (artSpaceObjects[artSpaceObjectsIndex].like)
                                Color(0xFFFF0000)
                            else
                                Color(0x34000000),
                            contentDescription = null)
                    }
                    Button(
                        onClick = {
                            artSpaceObjectsIndex = (artSpaceObjectsIndex + 1) % artSpaceObjects.size
                        }
                    ) {
                        Text(text = "Далее")
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