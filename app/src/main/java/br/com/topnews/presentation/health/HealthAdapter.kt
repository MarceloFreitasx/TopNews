package br.com.topnews.presentation.health

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.topnews.R
import br.com.topnews.data.models.HealthModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listitem_news.view.*

class HealthAdapter(
    private val news: List<HealthModel>,
    private val onItemClickListener: ((news: HealthModel) -> Unit)
) : RecyclerView.Adapter<HealthAdapter.HealthViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_news, parent, false);
        return HealthViewHolder(itemView, onItemClickListener);
    }

    override fun getItemCount() = news.count()

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        holder.bindView(news[position])
    }

    class HealthViewHolder(
        itemView: View,
        private val onItemClickListener: ((news: HealthModel) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val imagem = itemView.imagem
        private val titulo = itemView.titulo
        private val autor = itemView.autor

        fun bindView(news: HealthModel) {
            Picasso.get().load(news.cover).into(imagem);
            titulo.text = news.title
            autor.text = news.byline

            itemView.setOnClickListener() {
                onItemClickListener.invoke(news)
            }
        }
    }
}