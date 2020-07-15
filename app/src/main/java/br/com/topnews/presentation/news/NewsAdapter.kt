package br.com.topnews.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.topnews.R
import br.com.topnews.data.models.NewsModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.listitem_news.view.*

class NewsAdapter(
    private val news: List<NewsModel>,
    private val onItemClickListener: ((news: NewsModel, tag: String) -> Unit)
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_news, parent, false);
        return NewsViewHolder(itemView, onItemClickListener);
    }

    override fun getItemCount() = news.count()

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindView(position + 1, news[position])
    }

    class NewsViewHolder(
        itemView: View,
        private val onItemClickListener: (news: NewsModel, tag: String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val position: TextView = itemView.position
        private val imagem = itemView.imagem
        private val titulo = itemView.titulo
        private val autor = itemView.autor
        private val btnLido = itemView.btnLido

        fun bindView(i: Int, news: NewsModel) {
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