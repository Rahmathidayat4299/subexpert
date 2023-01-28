package com.dicoding

import android.app.Application
import com.dicoding.core.databaseModule
import com.dicoding.core.networkModule
import com.dicoding.core.repositoryModule
import com.dicoding.di.useCase
import com.dicoding.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Rahmat Hidayat on 28/01/2023.
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCase,
                    viewModel
                )
            )
        }
    }
}