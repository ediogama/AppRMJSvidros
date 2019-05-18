package br.com.ope_rmjs_vidros.helpers

import android.widget.EditText
import br.com.ope_rmjs_vidros.Produto
import br.com.ope_rmjs_vidros.ProdutoFormActivity
import br.com.ope_rmjs_vidros.R

class ProdutoFormHelper {
    var campoNome: EditText? = null
    var campoPreco: EditText? = null

    private var produto: Produto? = null

    constructor(activity: ProdutoFormActivity) {
        campoNome = activity.findViewById(R.id.form_nome_produto)
        campoPreco= activity.findViewById(R.id.form_preco)
        produto = Produto()
    }

    fun getProduto(): Produto {
        produto!!.nome = campoNome?.text.toString()
        produto!!.preco = campoPreco?.text.toString()

        return produto as Produto
    }

}