package dev.maxsiomin.qr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val VERSION = 1

/**
 * Contains history
 */
@Database(entities = [HistoryElement::class], version = VERSION)
abstract class HistoryDatabase : RoomDatabase() {

    /**
     * Returns new instance of [HistoryDao]
     */
    abstract fun historyDao(): HistoryDao

    companion object {

        private const val DATABASE_NAME = "cachingDatabase"

        // For history table
        const val HISTORY_TABLE_NAME = "historyTable"
        const val COLUMN_TEXT = "qr_text"

        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        /**
         * Returns instance of [HistoryDatabase]
         */
        fun getInstance(context: Context): HistoryDatabase {

            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        HistoryDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null
                return instance
            }
        }
    }
}
