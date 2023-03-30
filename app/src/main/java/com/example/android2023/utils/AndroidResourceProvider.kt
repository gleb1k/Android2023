package com.example.android2023.utils

import android.content.Context

class AndroidResourceProvider(
    private val context: Context
) : ResourceProvider {

    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getColor(id: Int): Int {
        return context.getColor(id)
    }
}
