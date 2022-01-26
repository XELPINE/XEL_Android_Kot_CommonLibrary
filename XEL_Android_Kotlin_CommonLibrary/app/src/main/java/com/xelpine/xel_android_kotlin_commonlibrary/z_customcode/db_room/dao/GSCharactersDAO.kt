package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity.GSCharacters

@Dao
interface GSCharactersDAO
{

    @Query("SELECT * FROM GSCharacters")
    fun getAll(): List<GSCharacters>

    @Insert
    fun insertAll(vararg gsCharacters: GSCharacters)

    @Delete
    fun delete(contacts: GSCharacters)
}