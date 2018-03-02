package ir.alilo.virustotalclient.datasources.api

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @FormUrlEncoded
    @POST(URLs.REPORT)
    fun report(@Field("resource") resource: String, @Field("apikey") apiKey: String = KeyStore.key): Call<Bagh>

    companion object {
        fun create(): API {
            val loggingInterceptor = HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            val okHttpBuilder = okhttp3.OkHttpClient.Builder().addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                    .baseUrl(URLs.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpBuilder.build())
                    .build()
            return retrofit.create(API::class.java)
        }
    }
}