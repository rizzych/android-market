package com.example.arizonamarket

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arizonamarket.databinding.ActivityMainBinding
import com.example.arizonamarket.obj.DonateProduct
import com.example.arizonamarket.utils.MarketAdapter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class MainActivity : AppCompatActivity() {
    val elements = listOf(
        R.id.menu_all,
        R.id.menu_box,
        R.id.menu_money,
        R.id.menu_car,
        R.id.menu_casino,
        R.id.menu_diamond,
        R.id.menu_star,
        R.id.menu_user
    )

    lateinit var binding: ActivityMainBinding
    private val adapter = MarketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        binding.apply {
            //setup count of recycle view
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 5)
            rcView.adapter = adapter

            //read json from assets
            val infoFromFile = getInfoFromFile("items.json")
            val donates: List<DonateProduct> = jacksonObjectMapper().readValue(infoFromFile)

            //item click listener
            adapter.setOnItemClickListener(object : MarketAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        this@MainActivity,
                        "You tap on $position item",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            //define state
            adapter.addItem(donates)

            elements.forEach { element ->
                findViewById<ImageView>(element).setOnClickListener { view ->
                    //disable all previous element
                    elements.filter { it != element }
                        .forEach { findViewById<ImageView>(it).alpha = 0.25F }
                    //enable current element
                    view.alpha = 1F

                    when (view.id) {
                        R.id.menu_all -> adapter.addItem(donates)
                        R.id.menu_box -> adapter.addItem(filterByCategory(donates, "box"))
                        R.id.menu_money -> adapter.addItem(filterByCategory(donates, "money"))
                        R.id.menu_car -> adapter.addItem(filterByCategory(donates, "car"))
                        R.id.menu_diamond -> adapter.addItem(filterByCategory(donates, "diamond"))
                        R.id.menu_casino -> adapter.addItem(filterByCategory(donates, "casino"))
                        R.id.menu_user -> adapter.addItem(filterByCategory(donates, "user"))
                        R.id.menu_star -> adapter.addItem(filterByCategory(donates, "star"))
                    }
                }
            }
        }
    }

    private fun filterByCategory(
        donates: List<DonateProduct>,
        category: String
    ): List<DonateProduct> {
        return donates.filter { donate -> donate.category.equals(category) };
    }

    private fun getInfoFromFile(fileName: String): String {
        var outputFile = ""
        try {
            this@MainActivity.assets.open(fileName).bufferedReader().use {
                outputFile = it.readText()
            }
        } catch (e: Exception) {
            print(e.stackTrace)
        }
        return outputFile
    }

}