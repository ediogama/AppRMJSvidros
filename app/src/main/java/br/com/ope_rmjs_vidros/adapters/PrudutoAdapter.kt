package br.com.ope_rmjs_vidros.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.ope_rmjs_vidros.R
import br.com.ope_rmjs_vidros.modelo.Produto

class ProdutoAdapter (
    val produtos: List<Produto>,
    val onClick: (Produto) -> Unit): RecyclerView.Adapter<ProdutoAdapter.ProdutosViewHolder>() {

    class ProdutosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardPreco : TextView
        var cardView: CardView

        init {
            cardNome = view.findViewById(R.id.cardNome_produto)
            cardPreco= view.findViewById(R.id.cardPreco_produto)
            cardView = view.findViewById(R.id.card_produtos)

        }

    }

    override fun getItemCount(): Int {return this.produtos.size}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_produtos, parent, false)

        return ProdutosViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {

        val produto = produtos[position]

        holder.cardNome.text = produto.nome
        holder.cardPreco.text = produto.preco

        holder.itemView.setOnClickListener {onClick(produto)}
    }
}