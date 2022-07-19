package me.rajatdhamija.githubpullrequests.data.remote

import me.rajatdhamija.githubpullrequests.data.model.PullRequest
import me.rajatdhamija.githubpullrequests.data.remote.Endpoints.SQUARE_RETROFIT_PULLS
import me.rajatdhamija.githubpullrequests.utils.common.Constants.PAGE
import me.rajatdhamija.githubpullrequests.utils.common.Constants.PER_PAGE
import me.rajatdhamija.githubpullrequests.utils.common.Constants.STATE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET(SQUARE_RETROFIT_PULLS)
    suspend fun getPullRequests(
        @Query(PER_PAGE) perPage: Int,
        @Query(PAGE) page: Int,
        @Query(STATE) state:String
    ): Response<List<PullRequest>>

}