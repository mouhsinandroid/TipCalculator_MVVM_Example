package com.mouhsinbourqaiba.android.tipcalculator.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mouhsinbourqaiba.android.tipcalculator.R
import com.mouhsinbourqaiba.android.tipcalculator.databinding.ActivityMainBinding
import com.mouhsinbourqaiba.android.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity(), SaveDialogFragment.Callback, LoadDialogFragment.Callback {
    lateinit var  binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        setSupportActionBar(binding.toolbar)
    }

    override fun onSaveTip(name: String) {
        binding.vm?.saveCurrentTip(name)
        Snackbar.make(binding.root, "Save $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }

            R.id.action_load -> {
                showLoadDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun showLoadDialog() {
        val loadFragment = LoadDialogFragment()
        loadFragment.show(supportFragmentManager, "LoadDialog")
    }

    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager, "SaveDialog")
    }



    override fun onTipSelected(name: String) {
        TODO("Not yet implemented")
    }
}