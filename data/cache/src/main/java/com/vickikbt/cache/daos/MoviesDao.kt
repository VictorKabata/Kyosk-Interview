package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.InTheatersComingSoonEntity
import com.vickikbt.cache.models.PopularMovieShowEntity
import com.vickikbt.cache.models.Top250MovieShowEntity
import com.vickikbt.commons.Constants

@Dao
interface MoviesDao {

    @Query("SELECT * FROM In_Theaters_Movies_Table WHERE category=:category")
    suspend fun getInTheatersMovies(category: String = Constants.IN_THEATERS): List<InTheatersComingSoonEntity>

    @Query("SELECT * FROM Popular_Movies_Table WHERE category=:category")
    suspend fun getPopularMovies(category: String = Constants.POPULAR_MOVIE): List<PopularMovieShowEntity>

    @Query("SELECT * FROM Top_250_Movies_Table WHERE category=:category")
    suspend fun getTop250Movies(category: String = Constants.TOP_250_MOVIE): List<Top250MovieShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInTheatersMovies(inTheatersEntities: List<InTheatersComingSoonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularMovies(popularMoviesEntities: List<PopularMovieShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTop250Movies(top250MoviesEntities: List<Top250MovieShowEntity>)

    @Query("SELECT COUNT(*) FROM In_Theaters_Movies_Table WHERE category=:category")
    suspend fun isInTheaterMoviesCacheAvailable(category: String = Constants.IN_THEATERS): Int

    @Query("SELECT COUNT(*) FROM Popular_Movies_Table WHERE category=:category")
    suspend fun isPopularMoviesCacheAvailable(category: String = Constants.POPULAR_MOVIE): Int

    @Query("SELECT COUNT(*) FROM Top_250_Movies_Table WHERE category=:category")
    suspend fun isTop250MoviesCacheAvailable(category: String = Constants.TOP_250_MOVIE): Int

}