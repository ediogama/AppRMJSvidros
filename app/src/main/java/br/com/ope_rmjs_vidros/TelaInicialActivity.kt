package br.com.ope_rmjs_vidros

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import br.com.ope_rmjs_vidros.modelo.Cliente
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val botaoFornecedor = findViewById<Button>(R.id.botao_fornecedor)
        botaoFornecedor.setOnClickListener { onClickBotaoFornecedor() }

        val botaoCliente = findViewById<Button>(R.id.botao_clientes)
        botaoCliente.setOnClickListener { onClickBotaoCliente() }

        val botaoProduto = findViewById<Button>(R.id.botao_produtos)
        botaoProduto.setOnClickListener { onClickBotaoProduto() }

        val botaoOrcamento = findViewById<Button>(R.id.botao_orcamento)
        botaoOrcamento.setOnClickListener { onClickBotaoOrcamento() }

        configuraMenuLateral()

    }

    private fun onClickBotaoFornecedor() {
        val intent = Intent(this, FornecedorActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBotaoOrcamento() {
        val intent = Intent(this, OrcamentoActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBotaoProduto() {
        val intent = Intent(this, ProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBotaoCliente() {
        val intent = Intent(this, ClienteActivity::class.java)
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
                val intent = Intent(this, FornecedorActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_produtos ->{
                val intent = Intent(this, ProdutoActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_orcamentos ->{
                val intent = Intent(this, OrcamentoActivity::class.java)
                startActivity(intent)
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
