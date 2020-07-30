package ru.rsue.denis.model.studlife

import com.google.gson.annotations.SerializedName

data class GetStudLife (
    @SerializedName("studLife_short_name")
    val short_name_studLife:String,

    @SerializedName("studLife_description")
    val description_studLife:String,

    @SerializedName("studLife_basement")
    val basement_studLife:String,

    @SerializedName("studLife_phone")
    val phone_studLife:String
)