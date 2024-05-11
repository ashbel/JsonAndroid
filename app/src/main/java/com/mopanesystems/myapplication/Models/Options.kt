package com.mopanesystems.myapplication.Models

import com.google.gson.annotations.SerializedName


data class Options (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("text" ) var text : String? = null

)