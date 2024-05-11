package com.mopanesystems.myapplication.ViewModels

data class MySpinner( var name: String,
                      var type: String,
                      var properties: MySpinnerProperties,
                      var required_status: String,
                      var options: List<MySpinnerOptions>)

data class MySpinnerOptions(var name: String,
                            var text: String)

data class MySpinnerProperties(var text: String,
                               var searcheable: String)
