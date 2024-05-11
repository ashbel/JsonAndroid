package com.mopanesystems.myapplication.Models

import com.google.gson.annotations.SerializedName


data class Fields (

  @SerializedName("name"            ) var name           : String?            = null,
  @SerializedName("type"            ) var type           : String?            = null,
  @SerializedName("properties"      ) var properties     : Properties?        = Properties(),
  @SerializedName("required_status" ) var requiredStatus : String?            = null,
  @SerializedName("options"         ) var options        : ArrayList<Options> = arrayListOf()

)