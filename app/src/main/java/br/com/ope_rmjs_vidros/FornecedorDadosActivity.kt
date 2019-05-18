package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import br.com.ope_rmjs_vidros.services.FornecedorService

class FornecedorDadosActivity : AppCompatActivity() {
    var fornecedor: Fornecedor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedor_dados)

        if (intent.getSerializableExtra("fornecedor") is Fornecedor)
            fornecedor = intent.getSerializableExtra("fornecedor") as Fornecedor


        supportActionBar?.title = fornecedor?.nome

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var campoNome = findViewById<EditText>(R.id.form_nome_fornecedor)
        var campoEmail = findViewById<EditText>(R.id.form_email)
        var campoCNPJ = findViewById<EditText>(R.id.form_cnpj)

        campoNome?.setText(fornecedor?.nome)
        campoEmail?.setText(fornecedor?.email)
        campoCNPJ?.setText(fornecedor?.cnpj)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_fornecedor)
        botalSalvar.setOnClickListener { OnClickSalvar() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fornecedor_dados, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if  (id == R.id.fornecedor_remover) {
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
        if (this.fornecedor != null && this.fornecedor is Fornecedor) {

            Thread {
                FornecedorService.delete(this.fornecedor as Fornecedor)
                runOnUiThread {

                    finish()
                }
            }.start()
        }
    }


    private fun OnClickSalvar() {
        taskAtualizar(fornecedor)

        Toast.makeText(this, "fornecedor ${fornecedor} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAtualizar(fornecedor: Fornecedor?) {
        Thread {
            FornecedorService.save(fornecedor!!)
            runOnUiThread {finish()}
        }.start()
    }
}
