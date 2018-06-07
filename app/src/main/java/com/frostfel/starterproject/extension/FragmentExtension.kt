package com.frostfel.starterproject.extension
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity


/**
 * Created by alvaro on 7/2/18.
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, fragmentId: Int) : Fragment {
    supportFragmentManager.inTransaction { add(fragmentId, fragment) }
    return fragment
}
fun AppCompatActivity.replaceFragment(fragment : Fragment, fragmentId : Int){
    supportFragmentManager.inTransaction { replace(fragmentId,fragment)}
}
fun AppCompatActivity.replaceFragmentWithSlideAnim(fragment : Fragment, fragmentId : Int){
    supportFragmentManager.inTransaction { replace(fragmentId,fragment)}
}

//Parcelable examples

/*
fun Fragment.withDocumentList(documentList : ArrayList<Document>) : Fragment{
    val args = Bundle()
    args.putParcelableArrayList(DOCUMENT_KEY,documentList)
    this.arguments = args
    return this
}

fun Fragment.withDocument(document: Document): Fragment {
    val args = Bundle()
    args.putParcelable(DOCUMENT_KEY,document)
    this.arguments = args
    return this
}
        */