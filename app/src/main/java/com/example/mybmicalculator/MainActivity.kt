package com.example.mybmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.mybmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bmiSpinnerList = listOf(metric, english)
    private lateinit var adapter: ArrayAdapter<String>
    var isMetric = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            bmiSpinnerList
        )
        binding.bmiSpinner.adapter = adapter
        binding.bmiSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(bmiSpinnerList[p2]) {
                    metric -> {
                        binding.weightLevel.text= "KG"
                        binding.heightLevel.text= "M"
                        isMetric = true
                    }
                    english -> {
                        binding.weightLevel.text= "IBS"
                        binding.heightLevel.text= "IN"
                        isMetric = false
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.calculateBtn.setOnClickListener {
            val weightString = binding.weightEdit.text.toString()
            val heightString = binding.heightEdit.text.toString()
            if (weightString.isEmpty()) {
                binding.weightEdit.error = "enter the weight"
            } else if (heightString.isEmpty()) {
                binding.heightEdit.error = "enter the height"
            } else {
                val weight = weightString.toFloat()
                val height = heightString.toFloat()
                correctAnswer(weight, height)
            }
        }
    }

    private fun correctAnswer(weight: Float, height: Float) {
        if (isMetric) {
            val bmiAnswer = weight / (height * height)
            bmiRange(bmiAnswer)
        } else {
            val bmiAnswer = (703 * weight) / (height * height)
            bmiRange(bmiAnswer)
        }
    }

    private fun bmiRange(bmiAnswer: Float) {
        var bmi = ""
        if (bmiAnswer > 16) {
            bmi = "Severe Thinness"
        }
        else if (bmiAnswer in 16.0..17.0) {
            bmi = "Moderate Thinness"
        }
        else if (bmiAnswer in 17.0..18.5) {
            bmi = "Mild Thinness"
        }
        else if (bmiAnswer in 18.5..25.0) {
            bmi = "Normal"
        }
        else if (bmiAnswer in 25.0..30.0) {
            bmi = "Overweight"
        }
        else if (bmiAnswer in 30.0..35.0) {
            bmi = "Obese Class I"
        }
        else if (bmiAnswer in 35.0..40.0) {
            bmi = "Obese Class II"
        }
        else {
            bmi = "Obese Class III"
        }
        val intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra(score_key, bmiAnswer)
        intent.putExtra(classification_key, bmi)
        startActivity(intent)
    }
}