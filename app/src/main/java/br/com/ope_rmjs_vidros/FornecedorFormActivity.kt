package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.FornecedorFormHelper
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import br.com.ope_rmjs_vidros.services.ClienteService
import br.com.ope_rmjs_vidros.services.FornecedorService

class FornecedorFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedor_form)

        supportActionBar?.title = "Novo Fornecedor"

        val botalSalvar = findViewById<Button>(R.id.botao_salvar_fornecedor)
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
        var helper = FornecedorFormHelper(this)
        val fornecedor = helper?.getFornecedor()
        taskAdicionar(fornecedor!!)

        Toast.makeText(this, "fornecedor ${fornecedor} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAdicionar(fornecedor: Fornecedor) {
        Thread {
            FornecedorService.save(fornecedor)
            runOnUiThread {finish()}
        }.start()
    }
}
