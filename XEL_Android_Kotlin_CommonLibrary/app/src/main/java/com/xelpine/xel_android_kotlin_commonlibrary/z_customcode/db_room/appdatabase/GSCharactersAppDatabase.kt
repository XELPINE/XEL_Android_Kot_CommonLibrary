package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.appdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.dao.GSCharactersDAO
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity.GSCharacters

@Database(entities = [GSCharacters::class], version = 1, exportSchema = false)
abstract class GSCharactersAppDatabase : RoomDatabase()
{
    abstract fun gsCharactersDao(): GSCharactersDAO

    companion object {
        private var instance: GSCharactersAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): GSCharactersAppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    GSCharactersAppDatabase::class.java,
                    "db_gscharacters"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}