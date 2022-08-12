package com.example.filmsapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FilmListFragment : Fragment() {

    private val viewModel: FilmListViewModel by lazy {
        ViewModelProvider(this)[FilmListViewModel::class.java]
    }
    private lateinit var filmRecyclerView: RecyclerView
    private lateinit var adapter: FilmAdapter
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film_list, container, false)

        adapter = FilmAdapter(object : FilmAdapter.OnItemClickListener {
            override fun onItemClicked(film: Film) {
                dialogBuilder = AlertDialog.Builder(view.context)
                dialogBuilder.apply {
                    setMessage("Фильм \"${film.title}\" был нажат")
                    setCancelable(true)
                    setPositiveButton("OK") { dialog, _ -> dialog.cancel() }
                }

                dialog = dialogBuilder.create()
                dialog.show()
            }
        })
        filmRecyclerView = view.findViewById(R.id.film_recycler_view) as RecyclerView
        filmRecyclerView.layoutManager = LinearLayoutManager(context)
        filmRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilms()
        viewModel.filmsList.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.updateList(it)
            }
        }
    }
}