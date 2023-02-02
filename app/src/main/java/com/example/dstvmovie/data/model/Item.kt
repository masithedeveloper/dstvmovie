package com.example.dstvmovie.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id") val id: Long?,
    //val localId: Int,
    @Json(name = "label") val label: String?,
    val valueToOrderBy: String?,
    val type: String?,
    val rank: Long?,
    @Json(name = "synopsis") val synopsis: String?,
    val title: String?,
    val imageUrl: String?,
    val releaseDate: Long?,
) : Parcelable {
    fun getImagePath() = imageUrl
}