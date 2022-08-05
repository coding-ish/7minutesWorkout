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

        binding?.rbMetricUnits?.setOnClickListener {
            binding?.llMetricUnits?.visibility = View.VISIBLE
            binding?.etMetricUnitWeight?.requestFocus()
            binding?.llUSUnitHeight?.visibility = View.INVISIBLE
            binding?.llUSUnitWeight?.visibility = View.INVISIBLE
        }

        binding?.rbUsUnits?.setOnClickListener {
            binding?.llMetricUnits?.visibility = View.INVISIBLE
            binding?.llUSUnitHeight?.visibility = View.VISIBLE
            binding?.llUSUnitWeight?.visibility = View.VISIBLE
            binding?.etUSUnitWeight?.requestFocus()
        }

        binding?.btnCalculate?.setOnClickListener {

            if(validateMetricUnits()){
                val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue/(heightValue * heightValue)

                displayBMIResult(bmi)

            }else if(validateUSUnits()){
                val heightValueFeet: Float = binding?.etUSUnitHeightFeet?.text.toString().toFloat()

                val heightValueInches: Float = binding?.etUSUnitHeightInches?.text.toString().toFloat()

                val weightValue: Float = binding?.etUSUnitWeight?.text.toString().toFloat()

                val totHeightInch: Float = (heightValueFeet*12) + heightValueInches

                val bmi = weightValue/(totHeightInch * totHeightInch) * 703

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

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty() || ((binding?.rbMetricUnits?.isChecked == false)) || !(binding?.etMetricUnitWeight?.text!!.contains("[0-9]".toRegex()))){
            isValid = false
        } else if(binding?.etMetricUnitHeight?.text.toString().isEmpty() || (binding?.rbMetricUnits?.isChecked == false) || !(binding?.etMetricUnitHeight?.text!!.contains("[0-9]".toRegex()))){
            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean{

        var isValid = true

        if(binding?.etUSUnitWeight?.text.toString().isEmpty() || (binding?.rbUsUnits?.isChecked == false) || !(binding?.etUSUnitWeight?.text!!.contains("[0-9]".toRegex()))){

            isValid = false
        }

        if(binding?.etUSUnitHeightFeet?.text.toString().isEmpty() || binding?.etUSUnitHeightInches?.text.toString().isEmpty() || (binding?.rbUsUnits?.isChecked == false) || !(binding?.etUSUnitHeightFeet?.text!!.contains("[0-9]".toRegex())) || !(binding?.etUSUnitHeightInches?.text!!.contains("[0-9]".toRegex()))){
            isValid = false
        }

        return isValid
    }
}
