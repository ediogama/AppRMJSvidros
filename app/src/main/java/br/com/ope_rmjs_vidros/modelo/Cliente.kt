package br.com.ope_rmjs_vidros

import java.io.Serializable

class Cliente : Serializable{
    var id : Long? = 0
    var nome = ""
    var cpf = ""
    var endereco = ""



    override fun toString(): String {
        return nome
    }
}