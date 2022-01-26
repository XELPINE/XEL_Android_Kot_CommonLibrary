package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "GSCharacters")
data class GSCharacters
    (
    //@PrimaryKey(autoGenerate = true) val id:Long,
    @PrimaryKey
    val id:String,
    var name_kor : String,              // 이름
    var name_eng : String,              // 이름
    var skillResourcesId: String,   // 스킬 재료 ID
    var propertyId: String      // 속성ID
)
{

}