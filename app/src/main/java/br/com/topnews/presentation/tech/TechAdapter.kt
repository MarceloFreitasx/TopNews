package br.com.topnews.presentation.tech

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.topnews.R
import br.com.topnews.data.models.TechModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.listitem_news.view.*

class TechAdapter(
    private val news: List<TechModel>,
    private val onItemClickListener: ((news: TechModel, tag: String) -> Unit)
) : RecyclerView.Adapter<TechAdapter.TechViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_news, parent, false);
        return TechViewHolder(itemView, onItemClickListener);
    }

    override fun getItemCount() = news.count()

    override fun onBindViewHolder(holder: TechViewHolder, position: Int) {
        holder.bindView(position + 1, news[position])
    }

    class TechViewHolder(
        itemView: View,
        private val onItemClickListener: ((news: TechModel, tag: String) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val position: TextView = itemView.position
        private val imagem = itemView.imagem
        private val titulo = itemView.titulo
        private val autor = itemView.autor
        private val btnLido = itemView.btnLido

        fun bindView(i: Int, news: TechModel) {
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