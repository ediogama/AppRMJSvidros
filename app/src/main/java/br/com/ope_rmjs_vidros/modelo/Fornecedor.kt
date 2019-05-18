package br.com.ope_rmjs_vidros.modelo

import com.google.gson.GsonBuilder
import java.io.Serializable

class Fornecedor: Serializable {
    var id: Long = 0
    var nome = ""
    var email = ""
    var cnpj = ""

    override fun toString(): String {
        return "Fornecedor $nome"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)

    }
}