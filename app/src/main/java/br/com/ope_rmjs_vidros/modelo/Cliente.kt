package br.com.ope_rmjs_vidros.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "cliente")
class Cliente : Serializable{
    @PrimaryKey
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