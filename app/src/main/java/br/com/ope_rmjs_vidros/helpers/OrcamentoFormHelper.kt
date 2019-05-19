package br.com.ope_rmjs_vidros.helpers

import android.widget.EditText
import br.com.ope_rmjs_vidros.OrcamentoFormActivity
import br.com.ope_rmjs_vidros.R
import br.com.ope_rmjs_vidros.modelo.Orcamento

class OrcamentoFormHelper {
    var campoPreco: EditText? = null
    var campoCPF_do_cliente: EditText? = null
    var campoData: EditText? = null

    private var orcamento: Orcamento

    constructor(activity: OrcamentoFormActivity) {
        campoPreco = activity.findViewById(R.id.form_preco)
        campoCPF_do_cliente = activity.findViewById(R.id.form_cpf_do_cliente)
        campoData = activity.findViewById(R.id.form_data)
        orcamento = Orcamento()
    }

    fun getOrcamento(): Orcamento {
        orcamento.preco = campoPreco?.text.toString()
        orcamento.cpf_do_cliente = campoCPF_do_cliente?.text.toString()
        orcamento.data = campoData?.text.toString()

        return orcamento
    }

}