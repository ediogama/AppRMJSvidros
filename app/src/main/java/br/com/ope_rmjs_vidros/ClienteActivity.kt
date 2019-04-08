package br.com.ope_rmjs_vidros

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView

class ClienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        var listaClientes = findViewById<ListView>(R.id.lista_clientes)
        val clientes = listOf("Maria", "Douglas", "Pedro", "Josefina", "Carlos")
        listaClientes?.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
                startActivity(intent)
            }
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
