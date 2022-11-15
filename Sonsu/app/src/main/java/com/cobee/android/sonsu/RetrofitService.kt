package com.cobee.android.sonsu

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("study/{level}")
    fun getStudyData(
        @Path("level") level: Int
    ): Call<StudyData>

    @GET("and/note/{year}/{month}/{day}")
    fun getWrongData(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int,
//        @Path("userIdx") userIdx: Int,
    ): Call<WrongData>


    @GET("study/word/{wordIdx}")
    fun getStudyPlayData(
        @Path("wordIdx") wordIdx: Int
    ): Call<StudyPlayData>
}