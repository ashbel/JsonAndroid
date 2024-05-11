package com.mopanesystems.myapplication.Builders

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mopanesystems.myapplication.Adapters.CustomAdapter
import com.mopanesystems.myapplication.Models.Fields
import com.mopanesystems.myapplication.R

class ViewBuilder {

    private lateinit var context: Context
    constructor(context: Context){
        this.context = context
    }
    constructor() {}

    fun getInputType(inputType: String?): Int {
        return when (inputType) {
            "numberSigned" -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
            "numberDecimal" -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            else -> InputType.TYPE_CLASS_TEXT
        }
    }

   fun generateSpinner(field: Fields): Spinner {
        val border = GradientDrawable()
        border.setStroke(2, Color.WHITE)
        border.cornerRadius = 5F
        val spinner = Spinner(context)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        spinner.layoutParams = params
        params.setMargins(40, 40, 40, 40)
        spinner.tag = field.name
        val adapter = CustomAdapter(context,field.options)
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

     fun generateRadioGroup(field: Fields): Pair<RadioGroup, TextView> {
        val textview = TextView(context)
        textview.text = field.properties?.text
        val textViewParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        textViewParams.setMargins(40, 40, 40, 0)
        textview.layoutParams = textViewParams

        val radioGroup = RadioGroup(context)
        val radioParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        radioGroup.layoutParams = radioParams
        radioParams.setMargins(40, 40, 40, 40)
        radioGroup.orientation = RadioGroup.HORIZONTAL
        radioGroup.tag = field.name
        for (option in field.options){
            val radioButton = RadioButton(context)
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

     fun generateEditText(field: Fields): TextInputLayout {
        val editTextBorder = GradientDrawable()
        val textInputLayoutBorder = GradientDrawable()
        editTextBorder.setStroke(2, Color.TRANSPARENT)
        editTextBorder.cornerRadius = 5F
        textInputLayoutBorder.setStroke(2, Color.WHITE)
        textInputLayoutBorder.cornerRadius = 25F
        val yourFont =  ResourcesCompat.getFont(context, R.font.assistant);
        val textInputLayout = TextInputLayout(context, null, R.attr.customTextInputStyle)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textInputLayout.layoutParams = params
        params.setMargins(40, 40, 40, 40)
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE;
        textInputLayout.setBoxCornerRadii(25F, 25F, 25F, 25F);
        textInputLayout.startIconDrawable = getDrawable(context,R.drawable.baseline_person_24)
        textInputLayout.typeface = yourFont
        textInputLayout.hint = field.properties?.hint
        val editText = TextInputEditText(context)
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
}