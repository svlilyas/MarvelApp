package com.pi.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class AuthenticationInterceptor(private val apiKey: String, private val privateKey: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val timestamp = Timestamp(System.currentTimeMillis()).time.toString()

        val url = it.url.newBuilder()
            .addQueryParameter("apikey", apiKey)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", getHash(timestamp))
            .build()

        val newRequest = it.newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }

    private fun getHash(timeStamp: String): String {
        val input = "$timeStamp$privateKey$apiKey"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
