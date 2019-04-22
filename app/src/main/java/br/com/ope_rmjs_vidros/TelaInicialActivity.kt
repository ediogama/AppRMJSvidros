package br.com.ope_rmjs_vidros

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private val context: Context get() = this
    var recyclerClientes: RecyclerView? = null
    private var clientes = listOf<Cliente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        configuraMenuLateral()

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
        clientes = ClienteService.getClientes(context)
        recyclerClientes?.adapter = ClienteAdapter(clientes) { OnClickCliente(it) }
    }

     fun OnClickCliente(cliente: Cliente) {
         Toast.makeText(context, "Clicou cliente ${cliente.nome}", Toast.LENGTH_SHORT).show()
         val intent = Intent(context, ClienteActivity::class.java)
         intent.putExtra("cliente", cliente)
         startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(this@TelaInicialActivity, newText, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@TelaInicialActivity, query, Toast.LENGTH_SHORT).show()
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.action_buscar ->{

                Toast.makeText(this, "Clicou em Buscar", Toast.LENGTH_SHORT).show()
            }
            R.id.action_adicionar ->{
                Toast.makeText(this, "Clicou em Adicionar", Toast.LENGTH_SHORT).show()
            }
            R.id.action_config ->{
                val intent = Intent(this, ConfigActivity::class.java)
                startActivity(intent)
            }
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraMenuLateral(){

        val toogle = ActionBarDrawerToggle(this,
                                                layoutMenuLateral,
                                                toolbar,
                                                R.string.navigation_drawer_open,
                                                R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_clientes ->{
                val intent = Intent(this, ClienteActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_fornecedores ->{
                Toast.makeText(this, "Clicou Fornecedores", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_produtos ->{
                Toast.makeText(this, "Clicou Produtos", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_config -> {
                val intent = Intent(this, ConfigActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_sair -> {
                finish()
            }
        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}
