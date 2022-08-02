package com.example.arizonamarket.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arizonamarket.R
import com.example.arizonamarket.obj.DonateProduct

class MarketAdapter :
    RecyclerView.Adapter<MarketHolder>() {
    private val donateList = ArrayList<DonateProduct>()

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_item, parent, false)
        return MarketHolder(view)
    }

    override fun onBindViewHolder(holder: MarketHolder, position: Int) {
        holder.bind(donateList[position], listener)
    }

    override fun getItemCount(): Int {
        return donateList.size
    }

    fun addItem(donateProduct: List<DonateProduct>) {
        donateList.clear()
        donateProduct.forEach { donateList.add(it) }
        notifyDataSetChanged()
    }
}