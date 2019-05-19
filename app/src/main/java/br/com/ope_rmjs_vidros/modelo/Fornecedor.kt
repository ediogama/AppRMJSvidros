package br.com.ope_rmjs_vidros.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "fornecedor")
class Fornecedor: Serializable {

    @PrimaryKey
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