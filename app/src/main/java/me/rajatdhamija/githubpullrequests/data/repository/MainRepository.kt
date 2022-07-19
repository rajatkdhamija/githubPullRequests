package me.rajatdhamija.githubpullrequests.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import me.rajatdhamija.githubpullrequests.data.model.PullRequest
import me.rajatdhamija.githubpullrequests.data.remote.PullRequestsPagingSource
import me.rajatdhamija.githubpullrequests.data.remote.NetworkService
import me.rajatdhamija.githubpullrequests.utils.common.Constants.NETWORK_PAGE_SIZE
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: NetworkService) {

    fun getAllPullRequests(): LiveData<PagingData<PullRequest>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                PullRequestsPagingSource(retrofitService)
            }, initialKey = 1
        ).liveData
    }

}