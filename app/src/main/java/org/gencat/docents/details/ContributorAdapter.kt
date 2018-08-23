package org.gencat.docents.details

import com.bumptech.glide.Glide
import org.gencat.docents.model.Contributor
import butterknife.ButterKnife
import butterknife.BindView
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.gencat.docents.R


internal class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {

    private val data: MutableList<Contributor> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_user_list_item, parent, false)
        return ContributorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    fun setData(contributors: List<Contributor>?) {
        if (contributors != null) {
            val diffResult = DiffUtil.calculateDiff(ContributorDiffCallback(data, contributors))
            data.clear()
            data.addAll(contributors)
            diffResult.dispatchUpdatesTo(this)
        } else {
            data.clear()
            notifyDataSetChanged()
        }
    }

     class ContributorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_user_name)
        lateinit var usernameText: TextView
        @BindView(R.id.iv_avatar)
        lateinit var avatarImageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(contributor: Contributor) {
            usernameText!!.setText(contributor.login)
            Glide.with(avatarImageView!!.getContext())
                    .load(contributor.avatarUrl)
                    .into(avatarImageView)
        }
    }
}
