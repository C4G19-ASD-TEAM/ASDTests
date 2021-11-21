package com.empresa.asdtests.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.empresa.asdtests.model.Usuario


@Dao
interface UserDAO {
    //Generamos las operaciones de la BDs

    //Select
    @Query( "SELECT * FROM usuario")
    fun getAll() : LiveData<List<Usuario>>


    @Query( "SELECT * FROM usuario WHERE id = :id")
    fun getUsuario( id: Int) : Usuario

    @Insert
    fun insert(usuario: Usuario)

    @Update
    fun actualizar(usuario: Usuario)

    @Delete
    fun eliminar(usuario: Usuario)

}