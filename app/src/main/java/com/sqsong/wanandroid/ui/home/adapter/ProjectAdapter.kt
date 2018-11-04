package com.sqsong.wanandroid.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.sqsong.wanandroid.R
import com.sqsong.wanandroid.common.holder.LoadingFooterViewHolder
import com.sqsong.wanandroid.common.holder.LoadingFooterViewHolder.LoadingState
import com.sqsong.wanandroid.data.HomeItem
import com.sqsong.wanandroid.util.Constants
import com.sqsong.wanandroid.util.DensityUtil
import com.sqsong.wanandroid.util.ext.showImage
import com.sqsong.wanandroid.view.CheckableImageView
import com.sqsong.wanandroid.view.LabelView

class ProjectAdapter(private val context: Context,
                     private val dataList: List<HomeItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mInflater = LayoutInflater.from(context)

    @LoadingState
    private var mLoadingState: Int = 0
    private var mActionListener: HomeItemAdapter.HomeItemActionListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (position == dataList.size) Constants.ITEM_TYPE_FOOTER else Constants.ITEM_TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.ITEM_TYPE_CONTENT) {
            ProjectViewHolder(mInflater.inflate(R.layout.item_project, parent, false))
        } else {
            LoadingFooterViewHolder(mInflater.inflate(R.layout.item_loading_footer, parent, false))
        }
    }

    override fun getItemCount(): Int = dataList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProjectViewHolder) {
            holder.bindData(dataList[position], position)
        } else if (holder is LoadingFooterViewHolder) {
            holder.updateLoadingState(mLoadingState)
        }
    }

    fun updateLoadingState(@LoadingState state: Int) {
        this.mLoadingState = state
        notifyItemChanged(dataList.size)
    }

    fun setHomeItemActionListener(listener: HomeItemAdapter.HomeItemActionListener) {
        this.mActionListener = listener
    }

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.previewIv)
        @JvmField
        var previewIv: ImageView? = null

        @BindView(R.id.titleTv)
        @JvmField
        var titleTv: TextView? = null

        @BindView(R.id.descTv)
        @JvmField
        var descTv: TextView? = null

        @BindView(R.id.labelView)
        @JvmField
        var labelView: LabelView? = null

        @BindView(R.id.authorTv)
        @JvmField
        var authorTv: TextView? = null

        @BindView(R.id.timeTv)
        @JvmField
        var timeTv: TextView? = null

        @BindView(R.id.heartIv)
        @JvmField
        var heartIv: CheckableImageView? = null

        @BindView(R.id.heartRl)
        @JvmField
        var heartRl: RelativeLayout? = null

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bindData(homeItem: HomeItem, position: Int) {
            titleTv?.text = homeItem.title
            descTv?.text = homeItem.desc
            authorTv?.text = homeItem.author
            timeTv?.text = homeItem.niceDate
            heartIv?.isChecked = homeItem.collect
            labelView?.visibility = if (homeItem.fresh) View.VISIBLE else View.INVISIBLE
            previewIv?.showImage(context, homeItem.envelopePic, R.drawable.image_placeholder)

            heartRl?.setOnClickListener {
                mActionListener?.onStarClick(homeItem, position)
            }

            itemView.setOnClickListener {
                mActionListener?.onListItemClick(homeItem, position)
            }

            val params = itemView.layoutParams as RecyclerView.LayoutParams
            if (position == dataList.size - 1) {
                params.bottomMargin = DensityUtil.dip2px(16)
            } else {
                params.bottomMargin = 0
            }
        }
    }

}