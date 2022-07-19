package me.rajatdhamija.githubpullrequests.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.rajatdhamija.githubpullrequests.data.model.PullRequest
import me.rajatdhamija.githubpullrequests.utils.common.Constants.ALL
import me.rajatdhamija.githubpullrequests.utils.common.Constants.NETWORK_PAGE_SIZE

class PullRequestsPagingSource(private val apiService: NetworkService) :
    PagingSource<Int, PullRequest>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getPullRequests(NETWORK_PAGE_SIZE, position, ALL)
            LoadResult.Page(
                data = response.body()!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.body().isNullOrEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}