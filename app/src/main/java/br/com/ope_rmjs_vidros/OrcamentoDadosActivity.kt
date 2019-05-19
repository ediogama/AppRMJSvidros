package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.ope_rmjs_vidros.modelo.Orcamento
import br.com.ope_rmjs_vidros.services.OrcamentoService

class OrcamentoDadosActivity : AppCompatActivity() {
    var orcamento: Orcamento? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orcamento_dados)

        if (intent.getSerializableExtra("orcamento") is Orcamento)
            orcamento = intent.getSerializableExtra("orcamento") as Orcamento


        supportActionBar?.title = "Orcamento do Cliente"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var campoPreco = findViewById<EditText>(R.id.form_preco)
        var campoCPF_do_Cliente = findViewById<EditText>(R.id.form_cpf_do_cliente)
        var campoData= findViewById<EditText>(R.id.form_data)

        campoPreco?.setText(orcamento?.preco)
        campoCPF_do_Cliente?.setText(orcamento?.cpf_do_cliente)
        campoData?.setText(orcamento?.data)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_orcamento)
        botalSalvar.setOnClickListener { OnClickSalvar() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_orcamento_dados, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if  (id == R.id.orcamento_remover) {
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
        if (this.orcamento != null && this.orcamento is Orcamento) {

            Thread {
                OrcamentoService.delete(this.orcamento as Orcamento)
                runOnUiThread {

                    finish()
                }
            }.start()
        }
    }


    private fun OnClickSalvar() {
        taskAtualizar(orcamento)

        Toast.makeText(this, "orcamento ${orcamento} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAtualizar(orcamento: Orcamento?) {
        Thread {
            OrcamentoService.save(orcamento!!)
            runOnUiThread {finish()}
        }.start()
    }
}
