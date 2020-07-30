package ru.rsue.denis.api.main

import retrofit2.Call
import retrofit2.http.GET
import ru.rsue.denis.model.main.MainItemModel

interface MainApiInterface {
    @GET("api/main/?format=json")
    fun fetchMain(): Call<List<MainItemModel>>
}