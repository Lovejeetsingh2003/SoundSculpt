package com.app.soundsculpt

import com.app.soundsculpt.entity.MusicEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MusicInterface {

    @Headers("X-RapidAPI-Key: f6cb7a55a4msh1586308c0e5518bp1c1c3cjsne2abff75caed",
    "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MusicEntity>
}