package com.github.moviesapi.utils


import androidx.fragment.app.Fragment
import com.github.moviesapi.MainActivity


fun Fragment.showProgress() {
    (activity as MainActivity).showProgress()
}

fun Fragment.hideProgress() {
    (activity as MainActivity).hideProgress()
}
fun Fragment.showToolbar() {
    (activity as MainActivity).showToolbar()
}

fun Fragment.hideToolbar() {
    (activity as MainActivity).hideToolbar()
}
