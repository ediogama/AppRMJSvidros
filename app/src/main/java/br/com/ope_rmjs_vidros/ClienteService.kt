package br.com.ope_rmjs_vidros

import android.content.Context

object ClienteService {
    fun getClientes (context: Context) : List<Cliente> {
        val clientes = mutableListOf<Cliente>()

        for (i in 1..10){
            val c = Cliente()
            c.nome = "Cliente $i"
            c.endereco = "Endereço $i"
            c.cpf = "CPF $i"
            clientes.add(c)
        }

        return clientes
    }
}