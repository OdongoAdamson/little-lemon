package com.littlelemon.adamson

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "menu")
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Query("SELECT (SELECT COUNT(*) FROM menu) == 0")
    fun isEmpty(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(menuItems: List<MenuItemRoom>)
}

@Database(
    entities = [MenuItemRoom::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
