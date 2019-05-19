package br.com.ope_rmjs_vidros.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "produto")
class Produto : Serializable{

    @PrimaryKey
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