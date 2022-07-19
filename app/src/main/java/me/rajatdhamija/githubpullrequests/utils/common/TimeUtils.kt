package me.rajatdhamija.githubpullrequests.utils.common

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.formatDate():String{
    val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val targetFormat: DateFormat = SimpleDateFormat("DD MMM, yyyy 'at' HH:mm a")
    val newDate: Date = originalFormat.parse(this)
    return targetFormat.format(newDate)
}