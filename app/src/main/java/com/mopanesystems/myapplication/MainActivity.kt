package com.mopanesystems.myapplication

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.mopanesystems.myapplication.Builders.ViewBuilder
import com.mopanesystems.myapplication.Models.Form


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val linearLayout = findViewById<LinearLayout>(R.id.linear)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val formName = findViewById<TextView>(R.id.form_name)
        val jsonString = assets.open("data.json").bufferedReader().use { it.readText() }
        val form = Gson().fromJson(jsonString, Form :: class.java)
        val fields = form.steps[0].fields
        formName.text = form.form
        var builder = ViewBuilder(this)
        for (field in fields) {
            when (field.type) {
                "text_input_edit_text" -> {
                    val textInputLayout = builder.generateEditText(field)
                    linearLayout?.addView(textInputLayout)
                }
                "radio_group" -> {
                    val (radioGroup,textView) = builder.generateRadioGroup(field)
                    linearLayout?.addView(textView)
                    linearLayout?.addView(radioGroup)
                }
                "spinner" -> {
                    val spinner = builder.generateSpinner(field)
                    linearLayout?.addView(spinner)
                }
                else -> {

                }
            }
        }

        val btn = Button(this)
        btn.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        btn.text = "Click me"
        btn.setOnClickListener {
            val values = getValues()
            for ((key, value) in values) {
                println("$key = $value")
                Toast.makeText(this,  "$key = $value" , Toast.LENGTH_SHORT).show()
            }
        }
        linearLayout?.addView(btn)
    }
    private fun getValues(): HashMap<String, String> {
        val linearLayout = findViewById<LinearLayout>(R.id.linear)
        val values = HashMap<String, String>()
        for (i in 0 until linearLayout.childCount) {
            when (val view = linearLayout.getChildAt(i)) {
                is TextInputLayout -> {
                    val innerLayout = view.getChildAt(0) as FrameLayout
                    val editText = innerLayout.getChildAt(0) as TextInputEditText
                    if(editText.text.toString().isNotEmpty())
                    values[view.tag as String] = editText.text.toString()
                }

                is Spinner -> {
                    if(view.selectedItem != null || view.selectedItem.toString().isNotEmpty())
                    values[view.tag as String] = view.selectedItem.toString()
                }

                is RadioGroup -> {
                    val radioButton = findViewById<RadioButton>(view.checkedRadioButtonId)
                    if(radioButton != null)
                    values[view.tag as String] = radioButton.text.toString()
                }
            }
        }
        return values
    }
}