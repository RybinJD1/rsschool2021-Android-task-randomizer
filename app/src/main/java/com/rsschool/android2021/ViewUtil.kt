package com.rsschool.android2021

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

class ViewUtil {

    companion object{

        fun showMessage(context: Context, message: String) {
            val builder = AlertDialog.Builder(context)
            with(builder)
            {
                setTitle("Warning")
                setMessage(message)
                setPositiveButton(
                    "OK"
                ) { dialog, which ->
                    Toast.makeText(context, android.R.string.ok, Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }

    }

}