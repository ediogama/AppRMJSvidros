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
import br.com.ope_rmjs_vidros.adapters.ClienteAdapter
import br.com.ope_rmjs_vidros.services.ClienteService

class ClienteActivity : AppCompatActivity() {
    private val context: Context get() = this
    var recyclerClientes: RecyclerView? = null
    private var clientes = listOf<Cliente>()
    var cliente: Cliente? = null
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Clientes"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerClientes = findViewById(R.id.recyclerClientes)
        recyclerClientes?.layoutManager = LinearLayoutManager(context)
        recyclerClientes?.itemAnimator = DefaultItemAnimator()
        recyclerClientes?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskClientes()
    }

    fun taskClientes(){
        Thread {
            clientes = ClienteService.getClientes(context)
            runOnUiThread {recyclerClientes?.adapter =
                ClienteAdapter(clientes) { OnClickCliente(it) }
            }
        }.start()
    }

    fun OnClickCliente(cliente: Cliente) {
        Toast.makeText(context, "Clicou cliente ${cliente.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ClienteDadosActivity::class.java)
        intent.putExtra("cliente", cliente)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_clientes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.clientes_adicionar -> {
                val intent = Intent(this, ClienteFormActivity::class.java)
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
            // atualizar lista de clientes
            taskClientes()
        }
    }
}
