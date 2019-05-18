package br.com.ope_rmjs_vidros

import com.google.gson.GsonBuilder
import java.io.Serializable

class Produto : Serializable{
    var id : Long = 0
    var fornecedor_id = ""
    var nome = ""
    var preco = ""

    override fun toString(): String {
        return "Produto: $nome"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)

    }
}