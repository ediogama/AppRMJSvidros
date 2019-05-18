package br.com.ope_rmjs_vidros

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class OrcamentoAdapter (
    val orcamentos: List<Orcamento>,
    val onClick: (Orcamento) -> Unit): RecyclerView.Adapter<OrcamentoAdapter.OrcamentosViewHolder>() {

    class OrcamentosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardPreco: TextView
        val cardCPF_do_Cliente : TextView
        val cardData: TextView
        var cardView: CardView

        init {
            cardPreco = view.findViewById(R.id.cardPreco_orcamento)
            cardCPF_do_Cliente = view.findViewById(R.id.cardCPF_do_cliente_orcamento)
            cardData = view.findViewById(R.id.cardData_orcamento)
            cardView = view.findViewById(R.id.card_orcamento)

        }

    }

    override fun getItemCount(): Int {return this.orcamentos.size}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrcamentosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_orcamento, parent, false)

        return OrcamentosViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrcamentosViewHolder, position: Int) {

        val orcamento = orcamentos[position]

        holder.cardPreco.text = orcamento.preco
        holder.cardCPF_do_Cliente.text = orcamento.cpf_do_cliente
        holder.cardData.text = orcamento.data

        holder.itemView.setOnClickListener {onClick(orcamento)}
    }
}
