package br.com.ope_rmjs_vidros.dao

import android.arch.persistence.room.*
import br.com.ope_rmjs_vidros.modelo.Fornecedor

@Dao
interface FornecedorDAO {

    @Query("SELECT * FROM fornecedor WHERE id =:id")
    fun getById(id: Long): Fornecedor?

    @Query("SELECT * FROM fornecedor")
    fun findAll(): List<Fornecedor>

    @Insert
    fun insert(fornecedor: Fornecedor)

    @Delete
    fun delete(fornecedor: Fornecedor)

    @Update
    fun update(fornecedor: Fornecedor)
}