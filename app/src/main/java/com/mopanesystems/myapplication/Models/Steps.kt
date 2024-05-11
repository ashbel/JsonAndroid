package com.mopanesystems.myapplication.Models

import com.google.gson.annotations.SerializedName

data class Steps (

  @SerializedName("title"  ) var title  : String?           = null,
  @SerializedName("fields" ) var fields : ArrayList<Fields> = arrayListOf()

)