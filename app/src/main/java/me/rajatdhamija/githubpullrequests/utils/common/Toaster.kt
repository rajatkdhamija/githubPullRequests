@file:Suppress("DEPRECATION")

package me.rajatdhamija.githubpullrequests.utils.common

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

object Toaster {
    fun show(context: Context, text: CharSequence) {
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            } else {
                val toast =
                    Toast.makeText(context, text, Toast.LENGTH_SHORT)
                toast.view?.background?.setColorFilter(
                    ContextCompat.getColor(context, android.R.color.white), PorterDuff.Mode.SRC_IN
                )
                val textView = toast.view?.findViewById(android.R.id.message) as TextView
                textView.setTextColor(ContextCompat.getColor(context, android.R.color.black))
                toast.show()
            }
        } catch (ex:Exception){
            ex.printStackTrace()
        }
    }
}