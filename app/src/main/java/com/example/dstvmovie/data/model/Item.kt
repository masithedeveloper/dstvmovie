package com.example.dstvmovie.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
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
    @Json(name = "label") val label: String?,
    @Json(name = "valueToOrderBy") val valueToOrderBy: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "rank") val rank: Int?,
    @Json(name = "synopsis") val synopsis: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "imageUrl") val imageUrl: String?,
    @Json(name = "releaseDate") val releaseDate: Long?,
    val localId: Long?,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean? = false
) : Parcelable {
    fun getImagePath() = imageUrl
}