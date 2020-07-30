package ru.rsue.denis.api.studlife

import retrofit2.Call
import retrofit2.http.GET
import ru.rsue.denis.model.studlife.GetStudLife

interface StudLifeApiInterface{
    val param:Int
    @GET("api/studlife/?format=json")
    fun fetchStudLife(): Call<List<GetStudLife>>
}