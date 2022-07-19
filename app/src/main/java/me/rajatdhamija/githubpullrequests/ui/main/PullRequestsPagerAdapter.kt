package me.rajatdhamija.githubpullrequests.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.rajatdhamija.githubpullrequests.data.model.PullRequest
import me.rajatdhamija.githubpullrequests.databinding.ItemPullRequestBinding
import me.rajatdhamija.githubpullrequests.utils.common.formatDate

class PullRequestsPagerAdapter :
    PagingDataAdapter<PullRequest, PullRequestsPagerAdapter.PullRequestViewHolder>(
        PullRequestComparator
    ) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = getItem(position)!!
        holder.view.tvPullRequestTitle.text = pullRequest.title
        holder.view.tvClosedAt.text =
            "Closed At: ${pullRequest.closed_at?.formatDate() ?: "It's an Open Request"}"
        holder.view.tvRequestedBy.text = "Requested By: ${pullRequest.user.login}"
        holder.view.tvCreatedAt.text = "Created At: ${pullRequest.created_at.formatDate()}"
        Glide.with(holder.itemView.context)
            .load(pullRequest.user.avatar_url)
            .circleCrop()
            .into(holder.view.ivUserImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPullRequestBinding.inflate(inflater, parent, false)
        return PullRequestViewHolder(binding)
    }

    class PullRequestViewHolder(val view: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    object PullRequestComparator : DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem == newItem
        }
    }
}

