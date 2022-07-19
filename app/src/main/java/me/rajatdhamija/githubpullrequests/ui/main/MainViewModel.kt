package me.rajatdhamija.githubpullrequests.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import me.rajatdhamija.githubpullrequests.data.model.PullRequest
import me.rajatdhamija.githubpullrequests.data.repository.MainRepository
import me.rajatdhamija.githubpullrequests.ui.base.BaseViewModel
import me.rajatdhamija.githubpullrequests.utils.network.NetworkHelper

class MainViewModel(
    networkHelper: NetworkHelper,
    var mainRepository: MainRepository

) : BaseViewModel(
    networkHelper
) {
    override fun onCreate() {}

    val errorMessage = MutableLiveData<String>()

    fun getPullRequests(): LiveData<PagingData<PullRequest>> {
        return mainRepository.getAllPullRequests().cachedIn(viewModelScope)
    }

}