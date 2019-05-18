package br.com.ope_rmjs_vidros.helpers

import android.widget.EditText
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import br.com.ope_rmjs_vidros.FornecedorFormActivity
import br.com.ope_rmjs_vidros.R

class FornecedorFormHelper {
    var campoNome: EditText? = null
    var campoCNPJ: EditText? = null
    var campoEmail: EditText? = null

    private var fornecedor: Fornecedor? = null

    constructor(activity: FornecedorFormActivity) {
        campoNome = activity.findViewById(R.id.form_nome_fornecedor)
        campoCNPJ = activity.findViewById(R.id.form_cnpj)
        campoEmail = activity.findViewById(R.id.form_email)
        fornecedor = Fornecedor()
    }

    fun getFornecedor(): Fornecedor? {
        fornecedor!!.nome = campoNome?.text.toString()
        fornecedor!!.cnpj= campoCNPJ?.text.toString()
        fornecedor!!.email = campoEmail?.text.toString()

        return fornecedor
    }

}