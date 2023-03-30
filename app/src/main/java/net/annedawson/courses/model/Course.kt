package net.annedawson.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * [Course] is the data class to represent the Course text, Int count and imageResourceId
 */
data class Course(
    @StringRes val stringResourceId: Int,
    val count: Int, // <- note I added this line so different from Affirmations app
    @DrawableRes val imageResourceId: Int
)
