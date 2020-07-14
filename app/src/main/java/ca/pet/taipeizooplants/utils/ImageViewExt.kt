package ca.pet.taipeizooplants.utils

import android.widget.ImageView
import ca.pet.taipeizooplants.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .apply(RequestOptions.placeholderOf(R.drawable.loading))
        .into(this)
}