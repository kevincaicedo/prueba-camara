package com.prueba.galery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Photo (

    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @NotNull
    val imageUrl: String,
    val name: String,
    val description: String
)