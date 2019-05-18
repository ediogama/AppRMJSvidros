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

class ProdutoActivity : AppCompatActivity() {
    private val context: Context get() = this
    var recyclerProdutos: RecyclerView? = null
    private var produtos = listOf<Produto>()
    var produto: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Produtos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerProdutos = findViewById(R.id.recyclerProdutos)
        recyclerProdutos?.layoutManager = LinearLayoutManager(context)
        recyclerProdutos?.itemAnimator = DefaultItemAnimator()
        recyclerProdutos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskProdutos()
    }

    fun taskProdutos(){
        Thread {
            produtos = ProdutoService.getProdutos(context)
            runOnUiThread {recyclerProdutos?.adapter = ProdutoAdapter(produtos) { OnClickProduto(it) }}
        }.start()
    }

    fun OnClickProduto(produto: Produto) {
        Toast.makeText(context, "Clicou produto ${produto.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_produtos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.produto_adicionar -> {
                val intent = Intent(this, ProdutoFormActivity::class.java)
                startActivity(intent)
            }
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
