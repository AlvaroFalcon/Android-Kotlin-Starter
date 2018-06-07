package com.frostfel.starterproject.extension

import android.app.Activity
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Point
import android.view.Display
import android.view.View
import android.widget.ImageView


/**
 * Created by alvaro on 12/4/18.
 */

fun Activity.resizeImage(imageResource : Int) : Drawable{
    val display = windowManager.defaultDisplay
    val deviceWidth = display.getWidth().toDouble()

    val bd = this.resources.getDrawable(imageResource) as BitmapDrawable
    val imageHeight = bd.bitmap.height.toDouble()
    val imageWidth = bd.bitmap.width.toDouble()

    val ratio = deviceWidth / imageWidth
    val newImageHeight = (imageHeight * ratio).toInt()

    val bMap = BitmapFactory.decodeResource(resources, imageResource)

    return BitmapDrawable(resources,getResizedBitmap(bMap, newImageHeight, deviceWidth.toInt()))
}

private fun getResizedBitmap(bm: Bitmap, newHeight: Int, newWidth: Int): Bitmap {

    val width = bm.width
    val height = bm.height

    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height

    // create a matrix for the manipulation
    val matrix = Matrix()

    // resize the bit map
    matrix.postScale(scaleWidth, scaleHeight)

    // recreate the new Bitmap
    val resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
            matrix, false)

    return resizedBitmap
}
fun Activity.scaleImageFromScrensize(image : ImageView, dividedBy : Float = 2f){
    val display : Display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    val height : Int = size.y
    val params = image.layoutParams
    params.height = (height/dividedBy).toInt()
    image.layoutParams = params
}
fun Activity.getScreenHeight() : Int{
    val display : Display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}

/** PERCENT style: 10% = 0.1, etc.. **/

fun View.getXPosition(percent : Float) : Float{
    return this.width * percent
}

fun View.getYPosition(percent : Float) : Float{
    return this.height * percent
}

