package com.example.topmovies.rest

import com.example.topmovies.adapter.FilmsAdapter

data class FilmList(
    val total_pages: Int,
    val results: List<FilmsAdapter.Film>)



