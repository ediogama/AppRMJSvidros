package br.com.ope_rmjs_vidros.dao

import android.arch.persistence.room.*
import br.com.ope_rmjs_vidros.modelo.Orcamento

@Dao
interface OrcamentoDAO {

    @Query("SELECT * FROM orcamento WHERE id =:id")
    fun getById(id: Long): Orcamento?

    @Query("SELECT * FROM orcamento")
    fun findAll(): List<Orcamento>

    @Insert
    fun insert(Orcamento: Orcamento)

    @Delete
    fun delete(Orcamento: Orcamento)

    @Update
    fun update(Orcamento: Orcamento)
}