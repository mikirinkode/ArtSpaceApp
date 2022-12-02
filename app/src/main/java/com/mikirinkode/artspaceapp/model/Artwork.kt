package com.mikirinkode.artspaceapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.mikirinkode.artspaceapp.R

data class Artwork(
    @DrawableRes val image: Int,
    val title: String,
    val artist: String,
    val year: Int
) {

    companion object {
        val artWorkList = listOf<Artwork>(
            Artwork(R.drawable.art1, "Electric Man", "Wafa", 2020),
            Artwork(R.drawable.art2, "Mysterious Man", "Wafa", 2021),
            Artwork(R.drawable.art3, "Dragon Kick 2", "Wafa", 2021),
            Artwork(R.drawable.art4, "Just the Two Of Us", "Wafa", 2021),
            Artwork(R.drawable.art5, "Nih Kita", "Wafa", 2020),
            Artwork(R.drawable.art6, "Dragon Kick", "Wafa", 2021),
            Artwork(R.drawable.art7, "A Man with Wolf Soul", "Wafa", 2022),
        )
    }
}

