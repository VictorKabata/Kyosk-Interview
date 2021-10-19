package com.vickikbt.network

import com.vickikbt.commons.Constants
import com.vickikbt.network.models.InTheatersComingSoonResponseDto
import com.vickikbt.network.models.PopularMoviesShowsResponseDto
import com.vickikbt.network.models.Top250MoviesShowsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //region Movies Endpoints
    @GET("InTheaters/{apiKey}")
    suspend fun fetchInTheaterMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<InTheatersComingSoonResponseDto>

    @GET("MostPopularMovies/{apiKey}")
    suspend fun fetchPopularMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<PopularMoviesShowsResponseDto>

    @GET("Top250Movies/{apiKey}")
    suspend fun fetchTop250Movies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<Top250MoviesShowsResponseDto>
    //endregion

    //region TV Shows Endpoints
    @GET("InTheaters/{apiKey}")
    suspend fun fetchComingSoon(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<InTheatersComingSoonResponseDto>

    @GET("MostPopularMovies/{apiKey}")
    suspend fun fetchPopularTvShows(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<PopularMoviesShowsResponseDto>

    @GET("Top250Movies/{apiKey}")
    suspend fun fetchTop250TvShows(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<Top250MoviesShowsResponseDto>
    //endregion

}