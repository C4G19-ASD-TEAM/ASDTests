package com.empresa.asdtests.dao

import com.empresa.asdtests.model.Pregunta
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PreguntaDAO {
    //Generamos las operaciones de la BDs

    //Select
    @Query( "SELECT * FROM pregunta")
    fun getAll() : LiveData<List<Pregunta>>

   // @Query ( "SELECT * FROM pregunta where protagonista = :protagonista")
   // fun getItemsXProtagonista(protagonista: String) : List<Pregunta>

    @Query ( "SELECT * FROM pregunta WHERE id = :id")
    fun getPregunta( id: Int) : Pregunta

    @Insert
    fun insert(pregunta: Pregunta)

    @Update
    fun actualizar(pregunta: Pregunta)

    @Delete
    fun eliminar(pregunta: Pregunta)
}