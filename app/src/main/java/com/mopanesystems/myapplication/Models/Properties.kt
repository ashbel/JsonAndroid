package com.mopanesystems.myapplication.Models

import com.google.gson.annotations.SerializedName


data class Properties (

  @SerializedName("text"       ) var text       : String? = null,
  @SerializedName("searchable" ) var searchable : String? = null,
  @SerializedName("type"       ) var type       : String? = null,
  @SerializedName("input_type"   ) var input_type   : String? = null,
  @SerializedName("hint"      ) var hint            :  String? = null,
)