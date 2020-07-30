package ru.rsue.denis.api.studlife

import retrofit2.Call
import retrofit2.http.GET
import ru.rsue.denis.model.studlife.GetStudLifeCategory

interface StudLifeCategoryApiInterface {
    @GET("api/studlifecategory/?format=json")
    fun fetchStudLifeCategory(): Call<List<GetStudLifeCategory>>
}