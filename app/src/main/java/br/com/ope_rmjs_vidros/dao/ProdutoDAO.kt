package br.com.ope_rmjs_vidros.dao

import android.arch.persistence.room.*
import br.com.ope_rmjs_vidros.modelo.Produto

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM produto WHERE id =:id")
    fun getById(id: Long): Produto?

    @Query("SELECT * FROM produto")
    fun findAll(): List<Produto>

    @Insert
    fun insert(Produto: Produto)

    @Delete
    fun delete(Produto: Produto)

    @Update
    fun update(Produto: Produto)
}