package br.com.ope_rmjs_vidros

import com.google.gson.GsonBuilder
import java.io.Serializable

class Orcamento : Serializable{
    var id: Long = 0
    var preco = ""
    var cpf_do_cliente = ""
    var data = ""

    override fun toString(): String {
        return "Orcamento $preco"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)

    }
}