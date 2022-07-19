package me.rajatdhamija.githubpullrequests.data.model

data class PullRequest(
    val id: Int,
    val title: String,
    val created_at: String,
    val closed_at: String?,
    val user: User
)
