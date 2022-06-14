package com.example.contactapp.network


import com.example.contactapp.util.Constant
import retrofit2.http.FieldMap

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface TwilioApi {
    @FormUrlEncoded
    @POST(Constant.POST_URL)
    fun sendMessage(
        @Path(Constant.ACCOUNT_SID) accountSId: String?,
        @Header(Constant.AUTH_TOKEN) signature: String?,
        @FieldMap smsData: Map<String?, String?>?
    ): Call<ResponseBody?>?
}

