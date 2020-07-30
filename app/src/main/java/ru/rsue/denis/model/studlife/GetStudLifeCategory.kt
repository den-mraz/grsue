package ru.rsue.denis.model.studlife

import com.google.gson.annotations.SerializedName

data class GetStudLifeCategory (
    @SerializedName("studLifeCategory_name")
    val name_studLifeCategory:String
)