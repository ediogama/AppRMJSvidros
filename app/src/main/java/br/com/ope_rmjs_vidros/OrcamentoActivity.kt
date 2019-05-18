package br.com.ope_rmjs_vidros

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class OrcamentoActivity : AppCompatActivity() {

    private val context: Context get() = this
    var recyclerOrcamentos: RecyclerView? = null
    private var orcamentos = listOf<Orcamento>()
    var orcamento: Orcamento? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orcamento)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Orçamentos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerOrcamentos = findViewById(R.id.recyclerOrcamentos)
        recyclerOrcamentos?.layoutManager = LinearLayoutManager(context)
        recyclerOrcamentos?.itemAnimator = DefaultItemAnimator()
        recyclerOrcamentos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskOrcamentos()
    }

    fun taskOrcamentos(){
        Thread {
            orcamentos = OrcamentoService.getOrcamentos(context)
            runOnUiThread {recyclerOrcamentos?.adapter = OrcamentoAdapter(orcamentos) { OnClickOrcamento(it) }}
        }.start()
    }

    fun OnClickOrcamento(orcamento: Orcamento) {
        Toast.makeText(context, "Clicou orcamento ${orcamento.preco}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, OrcamentoActivity::class.java)
        intent.putExtra("orcamento", orcamento)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_orcamentos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.orcamento_adicionar -> {
                val intent = Intent(this, OrcamentoFormActivity::class.java)
                startActivity(intent)
            }
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
