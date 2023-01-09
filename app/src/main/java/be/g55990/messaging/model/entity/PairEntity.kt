package be.g55990.messaging.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PairEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "key") val key: String,
)
