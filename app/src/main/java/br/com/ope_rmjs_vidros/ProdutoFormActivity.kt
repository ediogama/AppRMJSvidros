package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.ProdutoFormHelper
import br.com.ope_rmjs_vidros.modelo.Produto
import br.com.ope_rmjs_vidros.services.ClienteService
import br.com.ope_rmjs_vidros.services.ProdutoService

class ProdutoFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_form)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_produto)
        botalSalvar.setOnClickListener { OnClickSalvar() }

        supportActionBar?.title = "Novo Produto"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun OnClickSalvar() {
        var helper = ProdutoFormHelper(this)
        val produto = helper?.getProduto()
        taskAdicionar(produto)
        Toast.makeText(this, "Cliente ${produto} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAdicionar(produto: Produto) {
        Thread {
            ProdutoService.save(produto)
            runOnUiThread {finish()}
        }.start()
    }
}
