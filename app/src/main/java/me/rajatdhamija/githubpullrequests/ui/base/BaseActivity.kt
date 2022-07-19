package me.rajatdhamija.githubpullrequests.ui.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import me.rajatdhamija.githubpullrequests.MyApplication
import me.rajatdhamija.githubpullrequests.di.component.ActivityComponent
import me.rajatdhamija.githubpullrequests.di.component.DaggerActivityComponent
import me.rajatdhamija.githubpullrequests.di.module.ActivityModule
import me.rajatdhamija.githubpullrequests.utils.common.Toaster
import javax.inject.Inject

/**
 * Reference for generics: https://kotlinlang.org/docs/reference/generics.html
 * Basically BaseActivity will take any class that extends BaseViewModel
 */
abstract class BaseActivity<VM : BaseViewModel, VB:ViewBinding> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }


    private fun buildActivityComponent() = DaggerActivityComponent.builder()
        .applicationComponent((application as MyApplication).applicationComponent)
        .activityModule(ActivityModule(this)).build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
        viewModel.logoutUser.observe(this, Observer {
            finishAffinity()
        })
    }

    fun showMessage(message: String) = Toaster.show(applicationContext, message)

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    abstract fun getViewBinding(): VB

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)
}