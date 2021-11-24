package com.empresa.asdtests.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "usuario" )
data class Usuario (
    @PrimaryKey( autoGenerate = true )
    var id: Int,
    var username: String,
    var password: String,
    var role: String
)