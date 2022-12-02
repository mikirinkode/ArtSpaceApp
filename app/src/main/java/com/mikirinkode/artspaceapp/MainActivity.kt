package com.mikirinkode.artspaceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikirinkode.artspaceapp.model.Artwork
import com.mikirinkode.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artList = Artwork.artWorkList

    var currentIndex by remember {
        mutableStateOf(0)
    }

    var currentArtwork by remember {
        mutableStateOf(artList[currentIndex])
    }


    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ArtWorkWall(
            modifier = modifier.weight(2f),
            imageId = currentArtwork.image, imageDescription = currentArtwork.title
        )
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArtWorkDescriptor(
                title = currentArtwork.title,
                artist = currentArtwork.artist,
                year = currentArtwork.year
            )
            Spacer(modifier = modifier.height(16.dp))
            DisplayController(
                prevOnClick = {
                    val value = currentIndex - 1
                    if (value <= -1) {
                        currentIndex = artList.size - 1
                    } else {
                        currentIndex--
                    }
                    Log.e("MainActivity", "Index: $currentIndex")
                    currentArtwork = artList[currentIndex]
                },
                nextOnClick = {
                    val value = currentIndex + 1
                    if (value >= artList.size) {
                        currentIndex = 0
                    } else {
                        currentIndex++
                    }
                    Log.e("MainActivity", "Index: $currentIndex")
                    currentArtwork = artList[currentIndex]
                }
            )
        }
    }
}

@Composable
fun ArtWorkWall(modifier: Modifier = Modifier, imageId: Int, imageDescription: String) {
    Box(
        modifier = modifier
            .border(2.dp, Color.Black)
            .padding(24.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = imageDescription,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )
    }
}

@Composable
fun ArtWorkDescriptor(modifier: Modifier = Modifier, title: String, artist: String, year: Int) {
    Column(
        modifier
            .shadow(4.dp)
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title, fontSize = 24.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = artist,
                fontWeight = FontWeight.ExtraBold
            )
            Text(text = "($year)")
        }
    }
}

@Composable
fun DisplayController(
    modifier: Modifier = Modifier,
    prevOnClick: () -> Unit,
    nextOnClick: () -> Unit
) {
    Row() {
        Button(
            modifier = modifier.weight(1f),
            onClick = { prevOnClick() }
        ) {
            Text(text = "Prev")
        }
        Spacer(modifier = modifier.width(8.dp))
        Button(
            modifier = modifier.weight(1f),
            onClick = { nextOnClick() }
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}