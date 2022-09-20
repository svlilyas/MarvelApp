package com.pi.marvelapp.core.navigation

/**
 * A class for managing app's pages or
 * to use DeepLink feature and navigate to exact page easily
 */
object PageName {
    /**
     * MarvelApp's main domain
     */
    private const val marvelAppMain: String = "https://marvelapp.com.tr"

    /**
     * Base pages
     */
    object PreLogin {
        private const val preLogin: String = "$marvelAppMain/preLogin"

        /**
         * Page for listing all Marvel Characters
         */
        const val CHARACTER_DETAILS_PAGE: String = "$preLogin/characterList"

        /**
         * Page for viewing details about the marvel character which user selected
         */
        const val CHARACTER_LIST_PAGE: String = "$preLogin/characterDetails"
    }
}