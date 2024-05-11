package com.mopanesystems.myapplication

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.mopanesystems.myapplication.Adapters.CustomAdapter
import com.mopanesystems.myapplication.Models.Fields
import com.mopanesystems.myapplication.Models.Form
import java.io.File


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

        val jsonString = assets.open("data.json").bufferedReader().use { it.readText() }
        val form = Gson().fromJson(jsonString, Form :: class.java)
        val fields = form.steps[0].fields
        for (field in fields) {
            when (field.type) {
                "text_input_edit_text" -> {
                    val textInputLayout = generateEditText(field)
                    linearLayout?.addView(textInputLayout)
                }
                "radio_group" -> {
                    val (radioGroup,textView) = generateRadioGroup(field)
                    linearLayout?.addView(textView)
                    linearLayout?.addView(radioGroup)
                }
                "spinner" -> {
                    val spinner = generateSpinner(field)
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

    private fun generateEditText(field: Fields): TextInputLayout {
        val editTextBorder = GradientDrawable()
        val textInputLayoutBorder = GradientDrawable()
        editTextBorder.setStroke(2, Color.TRANSPARENT)
        editTextBorder.cornerRadius = 5F
        textInputLayoutBorder.setStroke(2, Color.WHITE)
        textInputLayoutBorder.cornerRadius = 25F
        val yourFont =  ResourcesCompat.getFont(this, R.font.assistant);
        val textInputLayout = TextInputLayout(this, null, R.attr.customTextInputStyle)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textInputLayout.layoutParams = params
        params.setMargins(40, 40, 40, 40)
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE;
        textInputLayout.setBoxCornerRadii(25F, 25F, 25F, 25F);
        textInputLayout.startIconDrawable = getDrawable(R.drawable.baseline_person_24)
        textInputLayout.typeface = yourFont
        textInputLayout.hint = field.properties?.hint
        val editText = TextInputEditText(this)
        editText.background = editTextBorder
        editText.inputType = getInputType(field.properties?.input_type)
        val editTextParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        editText.layoutParams = editTextParams
        editTextParams.setMargins(20, 40, 0, 0)
        textInputLayout.background = textInputLayoutBorder
        textInputLayout.addView(editText)
        textInputLayout.tag = field.name
        return textInputLayout
    }

    private fun getInputType(inputType: String?): Int {
        return when (inputType) {
            "numberSigned" -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
            "numberDecimal" -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            else -> InputType.TYPE_CLASS_TEXT
        }
    }

    private fun generateSpinner(field: Fields): Spinner {
        val border = GradientDrawable()
        border.setStroke(2, Color.WHITE)
        border.cornerRadius = 5F
        val spinner = Spinner(this)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        spinner.layoutParams = params
        params.setMargins(40, 40, 40, 40)
        spinner.tag = field.name
        val adapter = CustomAdapter(this,field.options)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
//                Toast.makeText(this@MainActivity,
//                    getString(R.string.selected_item) + " " +
//                            "" + field.options[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        return spinner
    }

    private fun generateRadioGroup(field: Fields): Pair<RadioGroup,TextView> {
        val textview = TextView(this)
        textview.text = field.properties?.text
        val textViewParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        textViewParams.setMargins(40, 40, 40, 0)
        textview.layoutParams = textViewParams

        val radioGroup = RadioGroup(this)
        val radioParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        radioGroup.layoutParams = radioParams
        radioParams.setMargins(40, 40, 40, 40)
        radioGroup.orientation = RadioGroup.HORIZONTAL
        radioGroup.tag = field.name
        for (option in field.options){
            val radioButton = RadioButton(this)
            radioButton.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            radioButton.text = option.text
            radioButton.tag = option.name
            radioGroup.addView(radioButton)
        }
        return Pair(radioGroup,textview)
    }

    private fun getValues(): HashMap<String, String> {
        val linearLayout = findViewById<LinearLayout>(R.id.linear)
        val values = HashMap<String, String>()
        for (i in 0 until linearLayout.childCount) {
            when (val view = linearLayout.getChildAt(i)) {
                is TextInputLayout -> {
                    val innerLayout = view.getChildAt(0) as FrameLayout
                    val editText = innerLayout.getChildAt(0) as TextInputEditText
                    values[view.tag as String] = editText.text.toString()
                }

                is Spinner -> {
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