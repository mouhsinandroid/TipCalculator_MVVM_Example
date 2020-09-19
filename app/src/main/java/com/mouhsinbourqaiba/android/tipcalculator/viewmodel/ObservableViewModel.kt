package com.mouhsinbourqaiba.android.tipcalculator.viewmodel

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer

abstract class ObservableViewModel(app:Application): AndroidViewModel(app), Observable {
    @delegate:Transient
    private val mCallback: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallback.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallback.remove(callback)
    }

    fun notifyChange() {
        mCallback.notifyChange(this, BR._all)
    }
}