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
import android.widget.Toast
import br.com.ope_rmjs_vidros.adapters.FornecedorAdapter
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import br.com.ope_rmjs_vidros.services.FornecedorService

class FornecedorActivity : AppCompatActivity() {
    private val context: Context get() = this
    var recyclerFornecedores: RecyclerView? = null
    private var fornecedores = listOf<Fornecedor>()
    var fornecedor: Fornecedor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedor)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Fornecedores"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerFornecedores = findViewById(R.id.recyclerFornecedores)
        recyclerFornecedores?.layoutManager = LinearLayoutManager(context)
        recyclerFornecedores?.itemAnimator = DefaultItemAnimator()
        recyclerFornecedores?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskFornecedores()
    }

    fun taskFornecedores(){
        Thread {
            fornecedores = FornecedorService.getFornecedores(context)
            runOnUiThread {recyclerFornecedores?.adapter =
                FornecedorAdapter(fornecedores) { OnClickFornecedor(it) }
            }
        }.start()
    }

    fun OnClickFornecedor(fornecedor: Fornecedor) {
        Toast.makeText(context, "Clicou fornecedor ${fornecedor.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, FornecedorActivity::class.java)
        intent.putExtra("fornecedor", fornecedor)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fornecedores, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.fornecedores_adicionar -> {
                val intent = Intent(this, FornecedorFormActivity::class.java)
                startActivity(intent)
            }
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
