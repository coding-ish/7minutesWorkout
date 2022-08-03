package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

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

                displayBMIResult(bmi)
            }
        }
    }

    private fun displayBMIResult(bmi: Float){

        val bmiLabel: String
        val bmiDescription:String

        if(bmi.compareTo(15f) <= 0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You need to eat more to be healthy!"
        }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f)<=0){
         bmiLabel = "Severely underweight"
         bmiDescription = "Oops! You need to eat more to be healthy!"
        }
        else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f) <=0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You need to eat more to be healthy!"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <=0){
            bmiLabel = "Normal"
            bmiDescription = "Congrats! You're in great shape!"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <=0){
            bmiLabel = "Overweight"
            bmiDescription = "Whoops! You need to work out more often!"
        }
        else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <=0) {
            bmiLabel = "Moderately obese"
            bmiDescription = "Whoops! You need to work out more often!"
        }
        else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <=0) {
            bmiLabel = "Severely obese"
            bmiDescription = "You need to act now! You are severely unhealthy!"
        }
        else{
            bmiLabel = "Very severely obese"
            bmiDescription = "You need to act now! You are severely unhealthy!"
        }

        var bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIResult?.text = bmiValue
        binding?.tvBMILabel?.text = bmiLabel
        binding?.tvMotivation?.text = bmiDescription

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