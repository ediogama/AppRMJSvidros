package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import br.com.ope_rmjs_vidros.helpers.OrcamentoFormHelper

class OrcamentoFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orcamento_form)
        supportActionBar?.title = "Orçamento"

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

        Toast.makeText(this, "orcamento ${orcamento} Salvo!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
