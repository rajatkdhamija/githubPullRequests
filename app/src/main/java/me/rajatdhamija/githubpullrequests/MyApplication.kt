package me.rajatdhamija.githubpullrequests

import android.app.Application
import me.rajatdhamija.githubpullrequests.di.component.ApplicationComponent
import me.rajatdhamija.githubpullrequests.di.component.DaggerApplicationComponent
import me.rajatdhamija.githubpullrequests.di.module.ApplicationModule

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}

