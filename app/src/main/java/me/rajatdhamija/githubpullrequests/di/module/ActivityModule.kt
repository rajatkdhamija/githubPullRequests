package me.rajatdhamija.githubpullrequests.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import me.rajatdhamija.githubpullrequests.data.repository.MainRepository
import me.rajatdhamija.githubpullrequests.ui.base.BaseActivity
import me.rajatdhamija.githubpullrequests.ui.main.MainViewModel
import me.rajatdhamija.githubpullrequests.utils.common.ViewModelProviderFactory
import me.rajatdhamija.githubpullrequests.utils.network.NetworkHelper


@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        networkHelper: NetworkHelper,
        mainRepository: MainRepository
    ): MainViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(
                networkHelper,
                mainRepository
            )
            //this lambda creates and return SplashViewModel
        }).get(MainViewModel::class.java)
}