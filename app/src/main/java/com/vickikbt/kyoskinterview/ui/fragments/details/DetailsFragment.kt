package com.vickikbt.kyoskinterview.ui.fragments.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentDetailsBinding
import com.vickikbt.kyoskinterview.ui.adapters.CastsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModel()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        initUI()
    }

    private fun initUI() {
        viewModel.getMovieShowById(args.id).observe(viewLifecycleOwner) {
            initMovieDetail(it)
        }

        viewModel.getPlot(args.id).observe(viewLifecycleOwner) {
            initPlot(it)
        }

        viewModel.getTrailer(args.id).observe(viewLifecycleOwner) {
            if (it != null) {
                initTrailer(it)
            } else {
                binding.textViewTrailerTitle.visibility = View.GONE
                binding.cardViewTrailer.visibility = View.GONE
            }
        }

        viewModel.getCast(args.id).observe(viewLifecycleOwner) {
            if (it != null) {
                initCast(it)
            } else {
                binding.textViewCastTitle.visibility = View.GONE
                binding.recyclerviewCast.visibility = View.GONE
            }
        }
    }

    private fun initMovieDetail(movieShow: MovieShow) {
        Glide.with(requireContext())
            .load(movieShow.image)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(binding.imageViewCover)

        binding.textViewTitle.text = movieShow.title

        val rating = Integer.parseInt(movieShow.imDbRating)

        Timber.e("Rating: $rating")

        if (rating != null && rating != 0) {
            val newRating = (rating * 5) / 10

            Timber.e("Calculated rating: $newRating")

            binding.textViewRating.text = newRating.toString()
            binding.ratingBarRating.rating = newRating.toFloat()
        } else {
            binding.textViewRating.visibility = View.GONE
            binding.ratingBarRating.visibility = View.GONE
        }
    }

    private fun initPlot(plot: PlotShort) {
        binding.textViewPlot.text = plot.plainText
    }

    private fun initTrailer(trailerResponse: TrailerResponse) {
        Glide.with(requireContext())
            .load(trailerResponse.thumbnailUrl)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(binding.imageViewTrailer)
    }

    private fun initCast(actors: List<Actor>) {
        binding.recyclerviewCast.apply {
            adapter = CastsAdapter(actors)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}