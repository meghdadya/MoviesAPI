package com.github.moviesapi.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.github.moviesapi.R

@BindingAdapter("app:imageUrl")
fun bindMoviePoster(imageView: ImageView, string: String?) {
    imageView.load(string) {
        crossfade(true)
       // transformations(RoundedCornersTransformation(5f))
    }
}