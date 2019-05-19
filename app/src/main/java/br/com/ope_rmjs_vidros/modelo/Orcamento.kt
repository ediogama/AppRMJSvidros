package br.com.ope_rmjs_vidros.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "orcamento")
class Orcamento : Serializable{

    @PrimaryKey
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