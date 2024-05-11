package com.mopanesystems.myapplication.Models

import com.google.gson.annotations.SerializedName


data class Form (

  @SerializedName("form"  ) var form  : String?          = null,
  @SerializedName("steps" ) var steps : ArrayList<Steps> = arrayListOf()

)