package com.mopanesystems.myapplication.ViewModels

data class MyRadioGroup(var name: String,
                            var type: String,
                            var properties: MyRadioGroupProperties,
                            var required_status: String,
                            var options: List<MyRadioGroupOptions>)

data class MyRadioGroupOptions(var name: String,
                            var text: String)

data class MyRadioGroupProperties(var text: String)
