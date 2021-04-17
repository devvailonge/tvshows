package com.devvailonge.tvshows.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.home.R

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = root.findViewById(R.id.rvCategories)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    val adapter = CategoryAdapter(getCategories())
        recyclerView.adapter = adapter

    }








    private fun getCategories (): List<Category> {
    return arrayListOf(
        Category("Action"),
        Category("Comedy"),
        Category("Crime"),
        Category("Adventure"),
        Category("Fantasy"),
        Category("Science Fiction"),
        Category("Suspense"),
        Category("Drama"),
        Category("Thriller"),
        Category("Horror"),
        Category("Romance"),
        Category("Children"),
        Category("Documentary"),
        Category("Family"),


        ).toList()
    }

    data class Category (val category: String  )

}