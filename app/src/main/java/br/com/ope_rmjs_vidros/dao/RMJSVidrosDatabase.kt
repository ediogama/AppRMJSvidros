package br.com.ope_rmjs_vidros.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.ope_rmjs_vidros.modelo.Cliente
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import br.com.ope_rmjs_vidros.modelo.Orcamento
import br.com.ope_rmjs_vidros.modelo.Produto

@Database(entities = arrayOf(Cliente::class, Fornecedor::class, Orcamento::class, Produto::class), version = 1)
abstract class RMJSVidrosDatabase : RoomDatabase() {

    abstract fun clienteDAO(): ClienteDAO
    abstract fun fornecedorDAO(): FornecedorDAO
    abstract fun orcamentoDAO(): OrcamentoDAO
    abstract fun produtoDAO(): ProdutoDAO
}