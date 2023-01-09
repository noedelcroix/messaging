package be.g55990.messaging.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeyEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "privateKey") val privateKey: String,
    @ColumnInfo(name = "publicKey") val publicKey: String
    )