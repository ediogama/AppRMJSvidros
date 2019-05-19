package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.ope_rmjs_vidros.modelo.Produto
import br.com.ope_rmjs_vidros.services.ProdutoService

class ProdutoDadosActivity : AppCompatActivity() {
    var produto: Produto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_dados)

        if (intent.getSerializableExtra("produto") is Produto)
            produto = intent.getSerializableExtra("produto") as Produto


        supportActionBar?.title = produto?.nome

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var campoNome = findViewById<EditText>(R.id.form_nome_produto)
        var campoPreco = findViewById<EditText>(R.id.form_preco)

        campoNome?.setText(produto?.nome)
        campoPreco?.setText(produto?.preco)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_produto)
        botalSalvar.setOnClickListener { OnClickSalvar() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_produto_dados, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if  (id == R.id.produto_remover) {
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a disciplina")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()
        }
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.produto != null && this.produto is Produto) {

            Thread {
                ProdutoService.delete(this.produto as Produto)
                runOnUiThread {

                    finish()
                }
            }.start()
        }
    }


    private fun OnClickSalvar() {
        taskAtualizar(produto)

        Toast.makeText(this, "produto ${produto} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAtualizar(produto: Produto?) {
        Thread {
            ProdutoService.save(produto!!)
            runOnUiThread {finish()}
        }.start()
    }
}
