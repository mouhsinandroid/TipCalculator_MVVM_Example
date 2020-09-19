package com.mouhsinbourqaiba.android.tipcalculator.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mouhsinbourqaiba.android.tipcalculator.R
import com.mouhsinbourqaiba.android.tipcalculator.model.RestaurantCalculator
import com.mouhsinbourqaiba.android.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(app:Application, val calculator: RestaurantCalculator = RestaurantCalculator()): ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    //lateinit var tipCalculation : TipCalculation

    val outputCheckAmount get()  = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
    val outputTotalDollarAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)

    val locationName get() = lastTipCalculated.locationName


    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)

    }


    fun loadTipCalculation(name: String) {
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)

    }


    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount!=null && tipPct!=null) {
            Log.d(TAG, "Check Amount : $checkAmount, TipPercentage : $tipPct")

            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            clearInputs()
        }


    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()

    }


}

private val TAG = CalculatorViewModel::class.simpleName
