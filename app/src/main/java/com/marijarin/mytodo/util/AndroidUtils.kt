package com.marijarin.mytodo.util

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

object AndroidUtils {
    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toDateFromLong(date: Long): String {
        return SimpleDateFormat("dd-MM-yyyy\nHH:mm", Locale.getDefault()).format(date)
    }
}