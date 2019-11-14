package com.perusudroid.androidassignment.data.model.product


data class ProductResponse( var image: Image? = null,
                            var name: String? = null,
                            var attributes: Attributes? = null,
                            var id: Int? = null)