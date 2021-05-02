package com.devvailonge.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.devvailonge.commons.viewBinding
import com.devvailonge.detail.R
import com.devvailonge.detail.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)
    private val adapter = DetailAdapter(getDetailFilms())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCastMovie.adapter = adapter

    }

    private fun getDetailFilms(): List<DetailFilm> {
        return arrayListOf(
            DetailFilm("http://t2.gstatic.com/licensed-image?q=tbn:ANd9GcSY2pGlF9O0Q9ByWyUv9eAzq_UdG9vcQQCuEbNS9O-akN10LbFez2L7D3b8HQVV"),
            DetailFilm("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcQQEm8_HDtjPqgJK8zoKWC-nAgvs89gTEQrKyHcLilSEaqO-bBES20GJphMKaID"),
            DetailFilm("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRdcWXvHCgo3L-0lPHXQyeg5Rl5GnKni1EvAalbQyLXrruaDpO1C_Z2O7uUyExA"),
            DetailFilm("http://t2.gstatic.com/licensed-image?q=tbn:ANd9GcSY2pGlF9O0Q9ByWyUv9eAzq_UdG9vcQQCuEbNS9O-akN10LbFez2L7D3b8HQVV"),
            DetailFilm("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRdcWXvHCgo3L-0lPHXQyeg5Rl5GnKni1EvAalbQyLXrruaDpO1C_Z2O7uUyExA"),
            DetailFilm("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcQQEm8_HDtjPqgJK8zoKWC-nAgvs89gTEQrKyHcLilSEaqO-bBES20GJphMKaID"),
            DetailFilm("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRdcWXvHCgo3L-0lPHXQyeg5Rl5GnKni1EvAalbQyLXrruaDpO1C_Z2O7uUyExA"),
            DetailFilm("http://t1.gstatic.com/licensed-image?q=tbn:ANd9GcSxftqk7CmGJwJtpG9gWQig_DBxsNerTgRpITpoktcpjREL-Rx81FeVW4Y8Preu")
        ).toList()
    }

    data class DetailFilm(val imageUrl: String)
}