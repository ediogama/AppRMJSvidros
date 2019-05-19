package br.com.ope_rmjs_vidros.dao

import android.arch.persistence.room.*
import br.com.ope_rmjs_vidros.modelo.Cliente

@Dao
interface ClienteDAO {

    @Query("SELECT * FROM cliente WHERE id =:id")
    fun getById(id: Long): Cliente

    @Query("SELECT * FROM cliente")
    fun findAll(): List<Cliente>

    @Insert
    fun insert(cliente: Cliente)

    @Delete
    fun delete(cliente: Cliente)

    @Update
    fun update(cliente: Cliente)
}