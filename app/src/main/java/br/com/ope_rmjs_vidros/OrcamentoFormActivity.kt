package br.com.ope_rmjs_vidros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class OrcamentoFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orcamento_form)
        supportActionBar?.title = "Orçamento"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
