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
import br.com.ope_rmjs_vidros.adapters.ProdutoAdapter
import br.com.ope_rmjs_vidros.modelo.Produto
import br.com.ope_rmjs_vidros.services.ProdutoService

class ProdutoActivity : AppCompatActivity() {
    private val context: Context get() = this
    var recyclerProdutos: RecyclerView? = null
    private var produtos = listOf<Produto>()
    var produto: Produto? = null
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

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
            runOnUiThread {recyclerProdutos?.adapter =
                ProdutoAdapter(produtos) { OnClickProduto(it) }
            }
        }.start()
    }

    fun OnClickProduto(produto: Produto) {
        Toast.makeText(context, "Clicou produto ${produto.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ProdutoDadosActivity::class.java)
        intent.putExtra("produto", produto)
        startActivityForResult(intent, REQUEST_REMOVE)
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
                startActivityForResult(intent, REQUEST_CADASTRO)
            }
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            taskProdutos()
        }
    }
}
