package com.example.mybmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybmicalculator.databinding.ActivityAnswerBinding

class AnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnswerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val scoreBMI = intent.getFloatExtra(score_key, 0.0f)
        val classification = intent.getStringExtra(classification_key)
        binding.scoreTextView.text = "score BMI $scoreBMI"
        binding.classificationTextView.text = "classification $classification"
    }
}