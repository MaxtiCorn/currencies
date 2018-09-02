package com.maxticorn.currencies.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maxticorn.currencies.R
import com.maxticorn.currencies.domain.Currency


class CurrenciesRecyclerAdapter : RecyclerView.Adapter<CurrenciesRecyclerAdapter.CurrencyViewHolder>() {
    var currencies: List<Currency>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies?.get(position)
        if (currency != null) {
            holder.currencyName.text = currency.name
            holder.currencyVolume.text = currency.volume
            holder.currencyAmount.text = currency.price.amount.toString()
        }
    }

    override fun getItemCount(): Int = currencies?.size ?: 0

    class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var currencyName: TextView = view.findViewById(R.id.currency_name)
        var currencyVolume: TextView = view.findViewById(R.id.currency_volume)
        var currencyAmount: TextView = view.findViewById(R.id.currency_amount)
    }
}