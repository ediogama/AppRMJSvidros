package br.com.ope_rmjs_vidros.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.ope_rmjs_vidros.R
import br.com.ope_rmjs_vidros.modelo.Fornecedor

class FornecedorAdapter (
    val fornecedores: List<Fornecedor>,
    val onClick: (Fornecedor) -> Unit): RecyclerView.Adapter<FornecedorAdapter.FornecedoresViewHolder>() {

    class FornecedoresViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardEmail: TextView
        val cardCNPJ: TextView
        var cardView: CardView

        init {
            cardNome = view.findViewById(R.id.cardNome_fornecedor)
            cardEmail = view.findViewById(R.id.cardEmail_fornecedor)
            cardCNPJ = view.findViewById(R.id.cardCNPJ_fornecedor)
            cardView = view.findViewById(R.id.card_fornecedor)

        }

    }

    override fun getItemCount(): Int {return this.fornecedores.size}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FornecedoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_fornecedor, parent, false)

        return FornecedoresViewHolder(view)
    }

    override fun onBindViewHolder(holder: FornecedoresViewHolder, position: Int) {

        val fornecedor = fornecedores[position]

        holder.cardNome.text = fornecedor.nome
        holder.cardEmail.text = fornecedor.email
        holder.cardCNPJ.text = fornecedor.cnpj

        holder.itemView.setOnClickListener {onClick(fornecedor)}
    }
}