package com.example.arizonamarket.obj

import com.fasterxml.jackson.annotation.JsonProperty

data class DonateProduct(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("uri")
    val uri: String,
    @JsonProperty("price")
    val price: Int,
    @JsonProperty("category")
    val category: String
)
