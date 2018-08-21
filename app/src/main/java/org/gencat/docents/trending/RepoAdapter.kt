package org.gencat.docents.trending

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import org.gencat.docents.R
import org.gencat.docents.model.Repo
import java.text.NumberFormat


internal class RepoAdapter(private val listener: RepoClickedListener) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    private val data = ArrayList<Repo>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_repo_list_item, parent, false)
        return RepoViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    fun setData(repos: List<Repo>?) {
        if (repos != null) {
            val diffResult = DiffUtil.calculateDiff(RepoDiffCallback(data, repos))
            data.clear()
            data.addAll(repos)
            diffResult.dispatchUpdatesTo(this)
        } else {
            data.clear()
            notifyDataSetChanged()
        }
    }

    internal class RepoViewHolder(itemView: View, listener: RepoClickedListener) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_repo_name)
        lateinit var repoNameText: TextView
        @BindView(R.id.tv_repo_description)
        lateinit var repoDescriptionText: TextView
        @BindView(R.id.tv_fork_count)
        lateinit var forkCountText: TextView
        @BindView(R.id.tv_star_count)
        lateinit var starCountText: TextView

        private lateinit var repo: Repo

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { view ->
                listener.onRepoClicked(repo)
            }
        }

        fun bind(repo: Repo) {
            this.repo = repo
            repoNameText!!.setText(repo.name)
            repoDescriptionText!!.setText(repo.description)
            forkCountText!!.setText(NumberFormat.getInstance().format(repo.forksCount))
            starCountText!!.setText(NumberFormat.getInstance().format(repo.stargazersCount))
        }
    }

    internal interface RepoClickedListener {

        fun onRepoClicked(repo: Repo)
    }
}
