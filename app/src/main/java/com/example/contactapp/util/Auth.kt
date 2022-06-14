package com.example.contactapp.util

import okhttp3.Credentials
import okhttp3.Interceptor

class Auth(username: String, password: String) : Interceptor {
        private var credentials: String = Credentials.basic(username, password)

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            request = request.newBuilder().header(Constant.AUTHORIZATION, credentials).build()

            return chain.proceed(request)

        }
    }

