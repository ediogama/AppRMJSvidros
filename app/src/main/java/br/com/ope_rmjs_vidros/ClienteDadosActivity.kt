package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.ClienteFormHelper
import br.com.ope_rmjs_vidros.services.ClienteService

class ClienteDadosActivity : AppCompatActivity() {
    var cliente: Cliente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_dados)

        if (intent.getSerializableExtra("cliente") is Cliente)
            cliente = intent.getSerializableExtra("cliente") as Cliente


        supportActionBar?.title = cliente?.nome

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var campoNome = findViewById<EditText>(R.id.form_nome)
        var campoTelefone = findViewById<EditText>(R.id.form_telefone)
        var campoEndereco = findViewById<EditText>(R.id.form_endereco)
        var campoCPF = findViewById<EditText>(R.id.form_cpf)

        campoNome?.setText(cliente?.nome)
        campoTelefone?.setText(cliente?.telefone)
        campoEndereco?.setText(cliente?.endereco)
        campoCPF?.setText(cliente?.cpf)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_cliente)
        botalSalvar.setOnClickListener { OnClickSalvar() }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_cliente_dado, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if  (id == R.id.clientes_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
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
        if (this.cliente != null && this.cliente is Cliente) {
            // Thread para remover a disciplina
            Thread {
                ClienteService.delete(this.cliente as Cliente)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }


    private fun OnClickSalvar() {
        taskAtualizar(cliente)

        Toast.makeText(this, "Cliente ${cliente} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAtualizar(cliente: Cliente?) {
        Thread {
            ClienteService.save(cliente)
            runOnUiThread {finish()}
        }.start()
    }
}
