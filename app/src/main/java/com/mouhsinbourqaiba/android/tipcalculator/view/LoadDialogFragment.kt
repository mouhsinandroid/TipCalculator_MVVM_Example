package com.mouhsinbourqaiba.android.tipcalculator.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.mouhsinbourqaiba.android.tipcalculator.R
import com.mouhsinbourqaiba.android.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*

class LoadDialogFragment: DialogFragment() {

    interface Callback {
        fun onTipSelected(name: String)
    }

    private var loadTipCallback : LoadDialogFragment.Callback? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadTipCallback = context as Callback
    }

    override fun onDetach() {
        super.onDetach()

        loadTipCallback = null
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = context?.let {ctx ->
            AlertDialog.Builder(ctx)
                .setView(crateView(ctx))
                .setNegativeButton(getString(R.string.action_cancel), null)
                .create()
        }

        return dialog!!
    }

    private fun crateView(ctx: Context): View {
        val rv = LayoutInflater
            .from(ctx)
            .inflate(R.layout.saved_tip_calculations_list, null)
            .recycler_saved_calculations

        rv.setHasFixedSize(true)
        rv.addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))

        val adapter = TipSummaryAdapter{
            loadTipCallback?.onTipSelected(it.locationName)
            dismiss()
        }

        rv.adapter = adapter
        val vm = ViewModelProviders.of(activity!!).get(CalculatorViewModel::class.java)


        return rv

    }


}