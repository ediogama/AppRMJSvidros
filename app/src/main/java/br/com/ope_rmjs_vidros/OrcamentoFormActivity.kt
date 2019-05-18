package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.OrcamentoFormHelper
import br.com.ope_rmjs_vidros.services.ClienteService
import br.com.ope_rmjs_vidros.services.OrcamentoService

class OrcamentoFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orcamento_form)


        val botalSalvar = findViewById<Button>(R.id.botao_salvar_orcamento)
        botalSalvar.setOnClickListener { OnClickSalvar() }

        supportActionBar?.title = "Novo Orçamento"

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
        var helper = OrcamentoFormHelper(this)
        val orcamento = helper?.getOrcamento()
        taskAdicionar(orcamento)
        Toast.makeText(this, "orcamento ${orcamento} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun taskAdicionar(orcamento: Orcamento) {
        Thread {
            OrcamentoService.save(orcamento)
            runOnUiThread {finish()}
        }.start()
    }
}
