package com.mouhsinbourqaiba.android.tipcalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.math.RoundingMode

class RestaurantCalculator(val repository: TipCalculationRepository= TipCalculationRepository()) {

    fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation {
        val tipAmount = checkAmount * (tipPct.toDouble()/100.0)
            .toBigDecimal()
            .setScale(2,RoundingMode.HALF_UP)
            .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
            checkAmount = checkAmount,
            tipPct = tipPct,
            tipAmount = tipAmount,
            grandTotal = grandTotal
        )
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}