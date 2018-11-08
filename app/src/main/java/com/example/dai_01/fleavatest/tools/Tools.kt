package com.example.dai_01.fleavatest

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.Toast
import java.text.NumberFormat
import java.util.*

object Tools {

    //Email Validation pattern
    val regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b"

    fun copyToClipboard(context: Context, data: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("clipboard", data)
        clipboard.primaryClip = clip
        Toast.makeText(context, "Nomor Invoice Disalin", Toast.LENGTH_SHORT).show()
    }

    fun createRotateAnimator(target: View, from: Float, to: Float): ObjectAnimator {
        val animator = ObjectAnimator.ofFloat(target, "rotation", from, to)
        animator.duration = 100
        animator.interpolator = LinearInterpolator()
        return animator
    }

    fun progressBar(act: Activity, message:String):ProgressDialog{
        val dialog = ProgressDialog(act)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setMessage(message)
        dialog.isIndeterminate = true
        dialog.setCanceledOnTouchOutside(false)
        dialog.cancel()
        return dialog
    }

    fun hideStatusBar(act: Activity) {

        act.requestWindowFeature(Window.FEATURE_NO_TITLE);
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    fun tranparentStatusBar(act: Activity) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true,act)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            act.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false,act)
            act.window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean, act: Activity) {
        val win = act.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun dateFormat(dateString : String):String{

        val dateNew = dateString.split("-")
        var date = dateNew[2]
        var month = dateNew[1]
        var year = dateNew[0]
        var monthNew  = ""

        if(date.toInt()<10){
            date = dateNew[2].replace("0","")
        }

//        debug("MONTH : $dateNew")

        if(month.equals("01")){
            monthNew = "Januari"
        }else if(month.equals("02")){
            monthNew = "Februari"
        }else if(month.equals("03")){
            monthNew = "Maret"
        }else if(month.equals("04")){
            monthNew = "April"
        }else if(month.equals("05")){
            monthNew = "Mei"
        }else if(month.equals("06")){
            monthNew = "Juni"
        }else if(month.equals("07")){
            monthNew = "Juli"
        }else if(month.equals("08")){
            monthNew = "Agustus"
        }else if(month.equals("09")){
            monthNew = "September"
        }else if(month.equals("10")){
            monthNew = "Oktober"
        }else if(month.equals("11")){
            monthNew = "November"
        }else if(month.equals("12")){
            monthNew = "Desember"
        }

        return date+" "+monthNew+" "+year

    }




}