package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener{

            onBackPressed()
        }

        binding?.btnCalculate?.setOnClickListener {
            if(validateMetricUnits()){
                val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue/(heightValue * heightValue)
            }
        }
    }

    private fun displayBMIResult(bmi: Float){

        val bmiLabel: String
        val bmiDescription:String

        if(bmi.compareTo(15f) <= 0){
            bmiLabel = "very severely underweight"
            bmiDescription = "Oops! You need to eat more to be healthy!"
        }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f)<=0){
         bmiLabel = "severely underweight"
         bmiDescription = "Oops! You need to eat more to be healthy!"
        }
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIResult?.text = bmi.toString()

    }

    private fun validateMetricUnits(): Boolean{

        var isValid = true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        } else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }

        return isValid
    }
}