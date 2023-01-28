package com.dicoding.core

import androidx.room.Room
import com.dicoding.core.data.source.RepositoryUser
import com.dicoding.core.data.source.local.room.LocalDataSource
import com.dicoding.core.data.source.local.room.UserDatabase
import com.dicoding.core.data.source.remote.RemoteDataSource
import com.dicoding.core.data.source.remote.network.ApiUser
import com.dicoding.core.domain.repository.IRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Rahmat Hidayat on 21/01/2023.
 */

private const val API_KEY = "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"
private const val BASE_URL = "https://api.github.com/"

val databaseModule = module {
    factory {
        get<UserDatabase>().favoriteUserDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java, "User.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiUser::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IRepository> {
        RepositoryUser(
            get(),
            get()
        )
    }
}
