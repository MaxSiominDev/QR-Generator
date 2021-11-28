package dev.maxsiomin.qr.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    /**
     * Returns list queries in [HistoryDatabase]
     */
    @Query(value = "SELECT * FROM 'historyTable'")
    suspend fun loadHistory(): List<HistoryElement>

    /**
     * Inserts a [element] to [HistoryDatabase]
     */
    @Insert
    suspend fun insertQuery(element: HistoryElement)

    /**
     * Returns Int value that represents how many elements are currently in history
     */
    @Query(value = "SELECT COUNT(id) FROM 'historyTable'")
    suspend fun getHistoryLength(): Int

    /**
     * Clears history
     */
    @Query(value = "DELETE FROM 'historyTable'")
    suspend fun clearHistory()
}
