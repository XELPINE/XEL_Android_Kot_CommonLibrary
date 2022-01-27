package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 파밍 재료 DB
 */
@Entity (tableName = "GSFarmingResources")
data class GSFarmingResources
    (
    //@PrimaryKey(autoGenerate = true) val id:Long,
    @PrimaryKey
    val id:String,
    var name_kor : String,              // 이름
    var name_eng : String,              // 이름
    var countryId: String,   // 국가ID
    var imageUrl: String,
    var coolTimeDurationSeconds: Int,      // 쿨타임 간격
    var nextGenTime: Int      // 다음 젠시간
)
{

}