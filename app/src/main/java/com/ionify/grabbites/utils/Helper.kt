package com.ionify.grabbites.utils


import android.content.Context
import androidx.appcompat.app.AlertDialog

object AlertDialogHelper{
    fun showAlertDialogNegative(
        context: Context,
        title: String,
        message: String,
        negativeButtonLabel: String,
    ) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setNegativeButton(negativeButtonLabel) { dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }
}