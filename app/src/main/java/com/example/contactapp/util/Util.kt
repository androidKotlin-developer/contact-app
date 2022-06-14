package com.example.contactapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Base64
import com.example.contactapp.model.Contact
import com.example.contactapp.network.TwilioApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.io.InputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun getFileData(context: Context?, fileName: String): Contact? {
        val jsonString: String
        try {
            val input: InputStream = context!!.assets.open(fileName)
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            jsonString = String(buffer, Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, Contact::class.java)
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormatter(inputFormat: String?, outputFormat: String?, datesToConvert: String?): String? {
        var dateToReturn = datesToConvert
        val sdf = SimpleDateFormat(inputFormat)
        sdf.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        val gmt: Date?
        val sdfOutPutToSend = SimpleDateFormat(outputFormat)
        sdfOutPutToSend.timeZone = TimeZone.getDefault()
        try {
            gmt = sdf.parse(datesToConvert!!)
            dateToReturn = sdfOutPutToSend.format(gmt!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateToReturn
    }


    fun sendMessage(smsBody: String) {
        val from = Constant.FROM_NUMBER
        val to = Constant.TO_NUMBER
        val base64EncodedCredentials = Base64.encodeToString(
            (Constant.ACCOUNT_SID + ":" + Constant.AUTH_TOKEN).toByteArray(),
            Base64.NO_WRAP
        )
        val smsData: HashMap<String?, String?> = HashMap()
        smsData[Constant.FROM] = from
        smsData[Constant.TO] = to
        smsData[Constant.BODY] = smsBody

        val client = OkHttpClient.Builder()
            .addInterceptor(Auth(Constant.ACCOUNT_SID, Constant.AUTH_TOKEN))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .build()
        val api = retrofit.create(TwilioApi::class.java)
        api.sendMessage(Constant.ACCOUNT_SID, base64EncodedCredentials, smsData)!!
            .enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {}
            })
    }
}
