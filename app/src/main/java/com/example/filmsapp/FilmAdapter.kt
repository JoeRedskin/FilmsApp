package com.example.filmsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private val filmsList = mutableListOf<Film>()

    interface OnItemClickListener {
        fun onItemClicked(film: Film)
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.film_title)
        private val directorTextView: TextView = itemView.findViewById(R.id.film_director)
        private val actorsTextView: TextView = itemView.findViewById(R.id.film_actors)

        fun bind(film: Film) {
            titleTextView.text = "${film.title}  (${film.releaseYear})"

            val lst = film.directorName.split(" ") as MutableList
            lst.add(0, lst.last())
            lst.removeAt(lst.count() - 1)
            val regex = """(\s[А-Яа-яёЁ]+)""".toRegex()

            directorTextView.text = regex.replace(lst.joinToString(separator = " "))
            { m -> " " + m.value[1] + "." }
            val actors = film.actors.distinct()
            actorsTextView.text = actors.joinToString { it.actorName }
        }
    }

    fun updateList(films: List<Film>) {
        val f = films.sortedBy { it.releaseYear }
        val cityDiffUtilCallback = FilmDiffUtilCallback(f)
        val cityDiffResult = DiffUtil.calculateDiff(cityDiffUtilCallback)
        cityDiffResult.dispatchUpdatesTo(this)
        filmsList.clear()
        filmsList.addAll(f)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmsList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(filmsList[position]);
        }
    }

    override fun getItemCount(): Int = filmsList.size

    inner class FilmDiffUtilCallback(private val newList: List<Film>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = filmsList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldFilm = filmsList[oldItemPosition]
            val newFilm = newList[newItemPosition]
            return oldFilm.title === newFilm.title
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldFilm = filmsList[oldItemPosition]
            val newFilm = newList[newItemPosition]
            return oldFilm == newFilm
        }
    }
}