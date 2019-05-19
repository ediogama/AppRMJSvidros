package br.com.ope_rmjs_vidros.dao

import android.arch.persistence.room.Room
import br.com.ope_rmjs_vidros.RMJSApplication

object DatabaseManager {

    private var dbInstance: RMJSVidrosDatabase

    init{
        val context = RMJSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(context, RMJSVidrosDatabase::class.java, "RMJSVidros.sqlite").build()
    }

    fun getClienteDAO(): ClienteDAO {
        return dbInstance.clienteDAO()
    }

    fun getFornecedorDAO(): FornecedorDAO {
        return dbInstance.fornecedorDAO()
    }

    fun getOrcamentoDAO(): OrcamentoDAO {
        return dbInstance.orcamentoDAO()
    }

    fun getProdutoDAO(): ProdutoDAO {
        return dbInstance.produtoDAO()
    }
}