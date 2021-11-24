package com.empresa.asdtests.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "pregunta" )
data class Pregunta(
    @PrimaryKey ( autoGenerate = true )
    var id: Int,
    var area: String,
    var pretexto: String,
    var opcion1: String,
    var respuesta: String
)

