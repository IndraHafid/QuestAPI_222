package com.example.questapi_222.repositori

import com.example.questapi_222.apiservice.ServiceApiSiswa
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

// 1. Interface Container untuk Dependency Injection
interface ContainerApp {
    val repositoryDataSiswa: RepositoryDataSiswa
}

// 2. Implementasi Default Container
class DefaultContainerApp : ContainerApp {

    private val baseurl = "http://10.0.2.2/umyTI/"

    // Konfigurasi Logging Interceptor untuk debug API
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Client HTTP menggunakan OkHttp
    val klien = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // Konfigurasi Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }.asConverterFactory("application/json".toMediaType())
        )
        .client(klien)
        .build()

    // Inisialisasi Service API secara Lazy
    private val retrofitService: ServiceApiSiswa by lazy {
        retrofit.create(ServiceApiSiswa::class.java)
    }

    // Implementasi Repository yang akan digunakan di ViewModel
    override val repositoryDataSiswa: RepositoryDataSiswa by lazy {
        JaringanRepositoryDataSiswa(retrofitService)
    }
}

// 3. Class Application untuk inisialisasi Container di tingkat aplikasi
class AplikasiDataSiswa : android.app.Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi container saat aplikasi dijalankan
        this.container = DefaultContainerApp()
    }
}