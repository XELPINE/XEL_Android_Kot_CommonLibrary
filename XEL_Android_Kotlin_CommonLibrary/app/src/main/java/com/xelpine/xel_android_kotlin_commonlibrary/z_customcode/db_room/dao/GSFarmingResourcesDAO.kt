package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity.GSCharacters
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity.GSFarmingResources

@Dao
interface GSFarmingResourcesDAO
{

    @Query("SELECT * FROM GSFarmingResources")
    fun getAll(): List<GSFarmingResources>

    @Insert
    fun insertAll(vararg gsFarmingResources: GSFarmingResources)

    @Delete
    fun delete(contacts: GSFarmingResources)
}