package br.com.ope_rmjs_vidros

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ClienteAdapter (
    val clientes: List<Cliente>,
    val onClick: (Cliente) -> Unit): RecyclerView.Adapter<ClienteAdapter.ClientesViewHolder>() {

        class ClientesViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val cardNome: TextView
            val cardEndereco : TextView
            val cardTelefone : TextView
            val cardCPF : TextView
            var cardView: CardView

            init {
                cardNome = view.findViewById(R.id.cardNome_cliente)
                cardEndereco = view.findViewById(R.id.cardEndereco_cliente)
                cardTelefone = view.findViewById(R.id.cardTelefone_cliente)
                cardCPF = view.findViewById(R.id.cardCPF_cliente)
                cardView = view.findViewById(R.id.card_clientes)

            }

        }

        override fun getItemCount(): Int {return this.clientes.size}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cliente, parent, false)

            return ClientesViewHolder(view)
        }

        override fun onBindViewHolder(holder: ClientesViewHolder, position: Int) {

            val cliente = clientes[position]

            holder.cardNome.text = cliente.nome
            holder.cardTelefone.text = cliente.telefone
            holder.cardEndereco.text = cliente.endereco
            holder.cardCPF.text = cliente.cpf

            holder.itemView.setOnClickListener {onClick(cliente)}
        }
}