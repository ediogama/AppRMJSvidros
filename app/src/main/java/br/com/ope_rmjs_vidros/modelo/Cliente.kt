package br.com.ope_rmjs_vidros

import com.google.gson.GsonBuilder
import java.io.Serializable

class Cliente : Serializable{
    var id : Long? = 0
    var nome = ""
    var telefone = ""
    var cpf = ""
    var endereco = ""



    override fun toString(): String {
        return "Cliente $nome"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)

    }
}