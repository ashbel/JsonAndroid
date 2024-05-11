package com.mopanesystems.myapplication.ViewModels

 data class MyEditText(
    var name: String,
    var type: String,
    var properties: MyEditTextProperties,
    var required_status: String,)

data class MyEditTextProperties(
    var hint: String,
    var type: String,
    var input_type: String,)

