package dev.maxsiomin.qr.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

@Suppress("SpellCheckingInspection")
private const val BASE_URL = "https://maxsiomin.dev/api/apps/qr_generator/"

private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UpdateApiService {

    /**
     * Returns double version value.
     * Example: 1.1
     */
    @GET("last_version.json")
    fun getLastVersion(): Call<Double>
}

object UpdateApi {
    val retrofitService: UpdateApiService by lazy {
        retrofit.create(UpdateApiService::class.java)
    }
}

