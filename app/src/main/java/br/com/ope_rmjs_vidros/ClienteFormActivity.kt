package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.ClienteFormHelper

class ClienteFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_form)

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_cliente)
        botalSalvar.setOnClickListener { OnClickSalvar() }
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

        Toast.makeText(this, "Cliente ${cliente} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
