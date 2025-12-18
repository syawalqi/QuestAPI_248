package com.example.questapi_248.repositori

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


interface ContainerApp {
    val repositoryDataSiswa : RepositoryDataSiswa
}

class DefaultContainerApp : ContainerApp{
    private val baseUrl = "http://10.0.2.2/QuestApi_248/"


    val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }

    val klien = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}
