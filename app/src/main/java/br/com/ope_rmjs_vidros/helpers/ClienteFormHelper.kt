package br.com.ope_rmjs_vidros.helpers

import android.widget.EditText
import br.com.ope_rmjs_vidros.Cliente
import br.com.ope_rmjs_vidros.ClienteFormActivity
import br.com.ope_rmjs_vidros.R

class ClienteFormHelper {
    var campoNome: EditText? = null
    var campoCPF: EditText? = null
    var campoEndereco: EditText? = null

    private var cliente: Cliente

    constructor(activity: ClienteFormActivity) {
        campoNome = activity.findViewById(R.id.form_nome)
        campoCPF = activity.findViewById(R.id.form_cpf)
        campoEndereco = activity.findViewById(R.id.form_endereco)
        cliente = Cliente()
    }

    fun getCliente(): Cliente {
        cliente.nome = campoNome?.text.toString()
        cliente.cpf = campoCPF?.text.toString()
        cliente.endereco = campoEndereco?.text.toString()

        return cliente
    }

}