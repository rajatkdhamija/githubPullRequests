package me.rajatdhamija.githubpullrequests.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Component
import me.rajatdhamija.githubpullrequests.MyApplication
import me.rajatdhamija.githubpullrequests.data.remote.NetworkService
import me.rajatdhamija.githubpullrequests.di.ApplicationContext
import me.rajatdhamija.githubpullrequests.di.module.ApplicationModule
import me.rajatdhamija.githubpullrequests.utils.network.NetworkHelper
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

}