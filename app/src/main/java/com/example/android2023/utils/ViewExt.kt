package com.example.android2023.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) = Snackbar
    .make(this, message, duration)
    .show()
