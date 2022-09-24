package com.pi.marvelapp.core.binding

import android.webkit.URLUtil
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo.Thumbnail
import com.pi.marvelapp.R
import com.pi.marvelapp.core.utils.createThumbnailUrl

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter(
        value = ["app:imageUrl"],
        requireAll = false
    )
    fun ImageView.loadImage(
        url: String?,
    ) {
        if (URLUtil.isValidUrl(url)) {
            this.load(url) {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_broken_image)
                networkCachePolicy(CachePolicy.ENABLED)
                memoryCachePolicy(CachePolicy.ENABLED)
                diskCachePolicy(CachePolicy.ENABLED)
                crossfade(true)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["app:thumbInfo"],
        requireAll = false
    )
    fun ImageView.loadThumb(
        thumb: Thumbnail?
    ) {

        val url = createThumbnailUrl(thumbnail = thumb)

        this.load(url) {
            error(R.drawable.ic_placeholder)
            placeholder(R.drawable.ic_placeholder)
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
            crossfade(true)
        }
    }
}
