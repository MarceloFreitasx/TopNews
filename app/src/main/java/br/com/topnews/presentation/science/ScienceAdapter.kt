package br.com.topnews.presentation.science

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.topnews.R
import br.com.topnews.data.models.ScienceModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listitem_news.view.*

class ScienceAdapter(
    private val news: List<ScienceModel>,
    private val onItemClickListener: ((news: ScienceModel) -> Unit)
) : RecyclerView.Adapter<ScienceAdapter.ScienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScienceViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_news, parent, false);
        return ScienceViewHolder(itemView, onItemClickListener);
    }

    override fun getItemCount() = news.count()

    override fun onBindViewHolder(holder: ScienceViewHolder, position: Int) {
        holder.bindView(position + 1, news[position])
    }

    class ScienceViewHolder(
        itemView: View,
        private val onItemClickListener: ((news: ScienceModel) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val position: TextView = itemView.position
        private val imagem = itemView.imagem
        private val titulo = itemView.titulo
        private val autor = itemView.autor

        fun bindView(i: Int, news: ScienceModel) {
            position.text = i.toString()
            Picasso.get().load(news.cover).into(imagem);
            titulo.text = news.title
            autor.text = news.byline

            itemView.setOnClickListener() {
                onItemClickListener.invoke(news)
            }
        }
    }
}