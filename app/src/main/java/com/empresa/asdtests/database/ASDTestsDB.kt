package com.empresa.asdtests.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.empresa.asdtests.dao.PreguntaDAO
import com.empresa.asdtests.dao.UsuarioDAO
import com.empresa.asdtests.model.Pregunta
import com.empresa.asdtests.model.Usuario

@Database (entities = [Pregunta::class, Usuario::class], version = 1)
abstract class ASDTestsDB : RoomDatabase() {
    //Operaciones de la BD
    abstract fun preguntaDAO() : PreguntaDAO
    abstract fun usuarioDAO() : UsuarioDAO

    //Instancia BD
    companion object{
        @Volatile
        private var INSTANCE : ASDTestsDB? = null

        fun getDataBase(context: Context) : ASDTestsDB {
            val tempInstance = INSTANCE

            if ( tempInstance != null){
                return tempInstance
            }
            synchronized( this ){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ASDTestsDB::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}