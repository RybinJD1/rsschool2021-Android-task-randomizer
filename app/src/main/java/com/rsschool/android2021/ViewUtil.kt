package com.rsschool.android2021

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

class ViewUtil {

    companion object {

        fun showMessage(context: Context, message: String) {
            val builder = AlertDialog.Builder(context)
            with(builder)
            {
                setTitle(context.resources.getString(R.string.warning))
                setMessage(message)
                setPositiveButton(
                    context.resources.getString(R.string.ok)
                ) { _, _ ->
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.invalid_data),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                show()
            }
        }

    }

}