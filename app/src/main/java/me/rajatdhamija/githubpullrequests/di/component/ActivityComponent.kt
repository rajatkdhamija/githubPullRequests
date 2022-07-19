package me.rajatdhamija.githubpullrequests.di.component

import dagger.Component
import me.rajatdhamija.githubpullrequests.di.ActivityScope
import me.rajatdhamija.githubpullrequests.di.module.ActivityModule
import me.rajatdhamija.githubpullrequests.ui.main.MainActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)

}