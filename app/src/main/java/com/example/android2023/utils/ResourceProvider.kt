package com.example.android2023.utils

interface ResourceProvider {

    fun getString(id: Int): String

    fun getColor(id: Int): Int
}
