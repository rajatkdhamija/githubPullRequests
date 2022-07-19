package me.rajatdhamija.githubpullrequests.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import me.rajatdhamija.githubpullrequests.MyApplication
import me.rajatdhamija.githubpullrequests.data.remote.NetworkService
import me.rajatdhamija.githubpullrequests.data.remote.Networking
import me.rajatdhamija.githubpullrequests.di.ApplicationContext
import me.rajatdhamija.githubpullrequests.utils.network.NetworkHelper
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "https://api.github.com/repos/",
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

}