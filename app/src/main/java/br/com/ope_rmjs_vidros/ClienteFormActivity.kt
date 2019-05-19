package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.ClienteFormHelper
import br.com.ope_rmjs_vidros.modelo.Cliente
import br.com.ope_rmjs_vidros.services.ClienteService

class ClienteFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_form)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_cliente)
        botalSalvar.setOnClickListener { OnClickSalvar() }

        supportActionBar?.title = "Novo Cliente"
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
        var helper = ClienteFormHelper(this)
        val cliente = helper?.getCliente()
        taskAdicionar(cliente)

        Toast.makeText(this, "Cliente ${cliente} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAdicionar(cliente: Cliente) {
        Thread {
            ClienteService.save(cliente)
            runOnUiThread {finish()}
        }.start()
    }
}
