package me.rajatdhamija.githubpullrequests.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.launch
import me.rajatdhamija.githubpullrequests.databinding.ActivityMainBinding
import me.rajatdhamija.githubpullrequests.di.component.ActivityComponent
import me.rajatdhamija.githubpullrequests.ui.base.BaseActivity
import me.rajatdhamija.githubpullrequests.utils.common.Toaster

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    private val adapter = PullRequestsPagerAdapter()
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        binding.recyclerview.adapter = adapter
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.progressDialog.isVisible = true
            else {
                binding.progressDialog.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
        lifecycleScope.launch {
            viewModel.getPullRequests().observe(this@MainActivity) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.errorMessage.observe(this) {
            Toaster.show(this, it)
        }
    }
}