package dev.maxsiomin.qr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.maxsiomin.qr.database.HistoryDatabase.Companion.COLUMN_TEXT
import dev.maxsiomin.qr.database.HistoryDatabase.Companion.HISTORY_TABLE_NAME

@Entity(tableName = HISTORY_TABLE_NAME)
data class HistoryElement(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = COLUMN_TEXT)
    val qrCodeText: String,
) {
    constructor(qrCodeText: String) : this(0, qrCodeText)

    override fun toString() = qrCodeText
}
