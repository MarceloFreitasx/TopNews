package br.com.topnews.presentation.arts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.topnews.R
import br.com.topnews.data.models.ArtsModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.listitem_news.view.*

class ArtsAdapter(
    private val news: List<ArtsModel>,
    private val onItemClickListener: ((news: ArtsModel, tag: String) -> Unit)
) : RecyclerView.Adapter<ArtsAdapter.ArtsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_news, parent, false);
        return ArtsViewHolder(itemView, onItemClickListener);
    }

    override fun getItemCount() = news.count()

    override fun onBindViewHolder(holder: ArtsViewHolder, position: Int) {
        holder.bindView(position + 1, news[position])
    }

    class ArtsViewHolder(
        itemView: View,
        private val onItemClickListener: ((news: ArtsModel, tag: String) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val position: TextView = itemView.position
        private val imagem = itemView.imagem
        private val titulo = itemView.titulo
        private val autor = itemView.autor
        private val btnLido = itemView.btnLido

        fun bindView(i: Int, news: ArtsModel) {
            position.text = i.toString()
            Glide.with(itemView.context).load(news.cover).into(imagem)
            titulo.text = news.title
            autor.text = news.byline

            itemView.setOnClickListener() {
                onItemClickListener.invoke(news, "item")
            }

            btnLido.setOnClickListener() {
                onItemClickListener.invoke(news, "lido")
            }
        }
    }
}