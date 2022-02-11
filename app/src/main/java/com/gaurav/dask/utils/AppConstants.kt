package com.gaurav.dask.utils

class AppConstants {
    companion object {
        val START_VIDEO_DURATION: Long = 1000 * 1000
        val VIDEO_DURATION: Long = 5 * 1000 * 1000
        const val AWS_URL = "https://s3.ap-southeast-1.amazonaws.com/"
        const val BUCKET_ID = "delightbucket"
        const val PROFILE_PATH = "/profile"
        const val SOCIAL_LOGIN = "social_login"
        const val SMS_LOGIN = "sms_login"
        const val PASSWORD_LOGIN = "password_login"
        const val TO_POST = "to_post"
        const val TO_HOME = "to_home"
        const val DELIGHT_MODEL = "OTPMODEL"
        const val STRIPE_GATEWAY = 1
        const val PAYSTACK_GATEWAY = 2
    }
}

object Category {
    const val RECOMMENDS = "1069"
    const val TOP_TITLES = "1070"
    const val CLUBS = "1126"
    const val GENRES = "1045"
    const val TOP_PICKS = "2260"
    const val SERIES="2259"
    const val KIDS="2289"
}