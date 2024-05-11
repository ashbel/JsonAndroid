package com.mopanesystems.myapplication

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import androidx.core.view.marginTop
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.mopanesystems.myapplication.Adapters.CustomAdapter
import com.mopanesystems.myapplication.Models.Fields
import com.mopanesystems.myapplication.Models.Form
import com.mopanesystems.myapplication.Models.Options


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

        val json1 = """
{
			"form": "Beekeeping",
			"steps": [
				{
					"title": "Beekeeping",
					"fields": [
						{
							"name": "a5ac708b-f7eb-4a71-b8a7-5afc2039c233",
							"type": "spinner",
							"properties": {
								"text": "Select Ward",
								"searchable": "Select One"
							},
							"required_status": "true:Please specify date",
							"options": [
								{
									"name": "ward_one",
									"text": "Ward 1"
								},
								{
									"name": "ward_two",
									"text": "Ward 2"
								},
								{
									"name": "ward_three",
									"text": "Ward 3"
								},
								{
									"name": "ward_four",
									"text": "Ward 4"
								},
								{
									"name": "Ward_five",
									"text": "Ward 5"
								},
								{
									"name": "Ward_Six",
									"text": "Ward 6"
								},
								{
									"name": "Ward_Seven",
									"text": "Ward 7"
								},
								{
									"name": "Ward_Eight",
									"text": "Ward 8"
								},
								{
									"name": "Ward_Nine",
									"text": "Ward 9"
								},
								{
									"name": "Ward_ten",
									"text": "Ward 10"
								},
								{
									"name": "Ward_Eleven",
									"text": "Ward 11"
								},
								{
									"name": "Ward_Twelve",
									"text": "Ward 12"
								},
								{
									"name": "Ward_Thirteen",
									"text": "Ward 13"
								},
								{
									"name": "Ward_Sixteen",
									"text": "Ward 16"
								},
								{
									"name": "Ward_Seventeen",
									"text": "Ward 17"
								},
								{
									"name": "Ward_Twenty two",
									"text": "Ward 22"
								},
								{
									"name": "Ward_Twenty three",
									"text": "Ward 23"
								}
							]
						},
						{
							"name": "3d6d9894-baae-4bd6-8e99-51e7f28788d0",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "No. of ToT Refresher Trainings",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "3c4656f1-8a19-4891-9033-513dc3894376",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "No. Ward level Trainings by Lead Beekeepers",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "08622b0c-a902-4a17-a67a-65f296867721",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "No. of Male Attendees",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "745e2545-421b-489e-a628-2fecd2a3b91c",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "No. of Female Attendees",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "ee734004-6102-4172-979b-2cf19a2a692a",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total No. of Training Attendees",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "aa5e2c42-ef07-4c17-8916-9da53c321136",
							"type": "radio_group",
							"properties": {
								"text": "Attendance Register Yes or No"
							},
							"required_status": "true:Please specify date",
							"options": [
								{
									"name": "Yes",
									"text": "Yes"
								},
								{
									"name": "No",
									"text": "No"
								}
							]
						},
						{
							"name": "dfbd8634-904f-4979-9486-b83dc1efe388",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Amount Paid (USD${'$'}${'$'}) for Ward base B/Keeping Training (ToT) ",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "1cc0dabd-affe-4700-9c08-0deb6baf2580",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total No. Beekeepers per Ward",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "d9867759-0ca6-4ebc-b976-82d0b023d9de",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total No. of Beekeepers who Harvested",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "7ff03675-d470-44ea-9707-c18dace99126",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Male Count",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "ba91fece-7596-433c-b335-6f5b683f75f2",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Female Count",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "42d7830c-0797-458d-81bb-5239122bb210",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total No. of KTB Occupied",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "c69e656a-e4d3-4e1a-b803-ba4f3d94fb03",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total No. of Trad Hives Occupied",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "462d4b5e-a811-49c9-b921-1709004197ef",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "New KTB Hives Dist.",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "d37df441-911e-4eae-b425-93af77a7cbd8",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Beekeeping Kits (Incl. bee suites, smokers etc)",
								"type": "name",
								"input_type": "numberSigned"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "b6e3e51f-930b-46df-9ce4-a70b51dede1b",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total Honey Harvested (KTB + Trad) Kgs",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "130a8160-0132-422a-9546-12d399e19294",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total Amount Sold (Kgs)",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "761f3b9a-514b-497a-a2a2-9670398f9e11",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Avg. Price/kg/USD",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "73c6f4d8-812d-4b32-8ccd-54d4b8f24e39",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total Income USD${'$'}${'$'}",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "84f67453-8c29-4287-a137-9a2e91f453c3",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Avg. Price/Kg/ZWL",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						},
						{
							"name": "c206a202-2d45-46c9-8512-b860505b359d",
							"type": "text_input_edit_text",
							"properties": {
								"hint": "Total Income ZWL${'$'}${'$'}",
								"type": "name",
								"input_type": "numberDecimal"
							},
							"required_status": "true:Please specify value"
						}
					]
				}
			]
		}"""
        val form = Gson().fromJson(json1, Form :: class.java)
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
        btn.setText("Click me")
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
                Toast.makeText(this@MainActivity,
                    getString(R.string.selected_item) + " " +
                            "" + field.options[position], Toast.LENGTH_SHORT).show()
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