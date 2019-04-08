package br.com.ope_rmjs_vidros

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class TelaInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val botaoClientes = findViewById<Button>(R.id.botao_clientes)
        botaoClientes.setOnClickListener { OnClickBotaoClientes() }

        val botaoOrcamento = findViewById<Button>(R.id.botao_orcamentos)
        botaoOrcamento.setOnClickListener { OnClickBotaoOrcamento() }

        val botaoProduto = findViewById<Button>(R.id.botao_produtos)
        botaoProduto.setOnClickListener { OnClickBotaoProduto() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun OnClickBotaoProduto() {
        val intent = Intent(this, ProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun OnClickBotaoOrcamento() {
        val intent = Intent(this, OrcamentoActivity::class.java)
        startActivity(intent)
    }

    private fun OnClickBotaoClientes() {
        val intent = Intent(this, ClienteActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        /**
         * --TENTEI IMPLEMENTAR O CODIGO ABAIXO PARA A BARRA DE PESQUISA, POREM CAUSA A PARADA DO APP E FECHA--
         *
         * (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(this@TelaInicialActivity, newText, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@TelaInicialActivity, query, Toast.LENGTH_SHORT).show()
                return false
            }

        })**/
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
}