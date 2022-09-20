package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grandpa.marvelapp.model.dto.SeriesDto

@Entity(tableName = "series_entity")
class SeriesEntity(
    @PrimaryKey
    @ColumnInfo(name = "series_id")
    var _id: Long,
    @ColumnInfo(name = "series_title")
    var title: String,
    @ColumnInfo(name = "series_description")
    var description: String,
    @ColumnInfo(name = "series_thumbnail")
    var thumbnail: String
) {
    fun toSeriesDto(): SeriesDto {
        return SeriesDto(_id, title, description, thumbnail)
    }
}


