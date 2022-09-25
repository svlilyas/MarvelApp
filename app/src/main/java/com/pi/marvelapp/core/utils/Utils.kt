package com.pi.marvelapp.core.utils

import com.pi.data.remote.response.CharacterListResponse
import com.pi.marvelapp.core.utils.AppConstants.Companion.DEFAULT_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun createThumbnailUrl(thumbnail: CharacterListResponse.Data.CharacterInfo.Thumbnail?): String = "${
    thumbnail?.path?.replace(
        AppConstants.PREFIX_HTTP,
        AppConstants.PREFIX_HTTPS
    )
}/${AppConstants.PREFIX_IMG_XLARGE}.${thumbnail?.extension}"

fun createDateRange(dateInfo: String): String {

    val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
    val dateString = simpleDateFormat.format(System.currentTimeMillis())
    val currentDate = String.format("%s", dateString)

    return "$dateInfo,$currentDate"
}