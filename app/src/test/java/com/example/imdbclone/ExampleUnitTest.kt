package com.example.imdbclone

import com.example.imdbclone.retrofit.TmdbApiInterface
import com.example.imdbclone.retrofit.TmdbClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun check_popular_Movies(){
        runBlocking {
            val res=TmdbClient.service.getPopularMovies(page="1")
            assertNotNull(res.body()?.results)
        }
    }
    @Test
    fun check_credits(){
        runBlocking {
            val res=TmdbClient.service.getCredits(508442)
            assertNotNull(res.body()?.cast)
        }
    }
    @Test
    fun check_upComing(){
        runBlocking {
            val res=TmdbClient.service.getUpComingMovies()
            assertNotNull(res.body()?.results)
        }
    }
    @Test
    fun check_trending(){
        runBlocking {
            val res=TmdbClient.service.getTrendingMovies()
            assertNotNull(res.body()?.results)
        }
    }
    @Test
    fun check_topRated(){
        runBlocking {
            val res=TmdbClient.service.getTopRatedMovies()
            assertNotNull(res.body()?.results)
        }
    }
}