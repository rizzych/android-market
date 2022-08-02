package com.example.arizonamarket.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.arizonamarket.R
import com.example.arizonamarket.databinding.MarketItemBinding
import com.example.arizonamarket.obj.DonateProduct
import com.squareup.picasso.Picasso

class MarketHolder(item: View) : RecyclerView.ViewHolder(item) {

    val binding = MarketItemBinding.bind(item)

    fun bind(donateProduct: DonateProduct, listner: MarketAdapter.OnItemClickListener) {
        binding.itemTittle.text = donateProduct.name
        Picasso
            .get()
            .load(donateProduct.uri)
            .placeholder(R.drawable.item)
            .into(binding.marketItem)
        binding.marketPrice.text = donateProduct.price.toString()

        itemView.setOnClickListener {
            listner.onItemClick(adapterPosition)
        }
    }
}