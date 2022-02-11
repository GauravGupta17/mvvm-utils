/*
package com.gaurav.dask.custom

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.*
import com.gaurav.dask.R
import kotlinx.android.synthetic.main.rv_item_error.view.*
import timber.log.Timber

class PaginatedListAdapter<M>(
    @LayoutRes private val layoutResId: Int,
    private val vhBuilder: VhBuilder,
    private var binder: Binder<M?>,
    areContentsSame: (old: M, new: M) -> Boolean,
    diffCallback: DiffUtil.ItemCallback<M> = getDiffCallback(
        areContentsSame
    ),
    private val loadMore: (page: Int) -> Unit
) : ListAdapter<M, RecyclerView.ViewHolder>(diffCallback) {

    var nextPage: Int? = 2

    private fun showNextPageLoading() {
        submitList(ArrayList(currentList).apply { add(null) })
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        // Load more when end is about to reach
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                recyclerView.layoutManager?.let { it ->
                    val totalItemCount = itemCount
                    val lastVisibleItemIndex = when (it) {
                        is LinearLayoutManager -> it.findLastCompletelyVisibleItemPosition()
                        is GridLayoutManager -> it.findLastCompletelyVisibleItemPosition()
                        else -> -1
                    }

                    Timber.v("onScrolled(): dy:$dy, page $nextPage, totalItemCount:$totalItemCount, visibleIndex+threshold = ${lastVisibleItemIndex + ITEMS_VISIBILITY_THRESHOLD}")
                    if (totalItemCount <= lastVisibleItemIndex + ITEMS_VISIBILITY_THRESHOLD*/
/* && dy > 0*//*
) {
                        ifNotLoading {
                            nextPage?.let { page ->
                                showNextPageLoading()
                                loadMore(page)
                                nextPage = nextPage?.plus(1)
                            }
                        }
                    }
                }
            }
        })

        // Set loading VH span to fill row
        (recyclerView.layoutManager as? GridLayoutManager)?.run {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (getItem(position) == null) spanCount else 1
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        //Timber.i("onCreateViewHolder() called with: viewType = [$viewType]")
        val itemView = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return when (viewType) {
            ITEM -> vhBuilder(itemView, viewType)
            else -> if (currentList.size != 1) LoadingVH(
                getLoadingView(parent.context)
            )
            else NoResultVH(
                getNoResultsView(parent)
            )
        }
    }

    private fun getNoResultsView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(R.layout.rv_item_error, parent, false)

    private fun getLoadingView(context: Context): View {
        val frameLayout = FrameLayout(context)
        val layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        frameLayout.layoutParams = layoutParams

        val progressBar = ProgressBar(context)
        val progressParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        progressParams.gravity = Gravity.CENTER_HORIZONTAL
        progressBar.layoutParams = layoutParams

        frameLayout.addView(progressBar)
        return frameLayout
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        binder(holder, getItem(position))
    }


    override fun submitList(list: MutableList<M?>?) {
        if (list?.size == 0) {
            list.add(null)
        }
        super.submitList(list) {
            notifyDataSetChanged()
        }
        Timber.i("submitList() called with: list sizeName= [${list?.size}]")
    }

    private fun ifNotLoading(notLoading: () -> Unit) {
        val list = currentList
        if (list.lastOrNull() != null) {
            notLoading()
        }
    }

    fun setAsLastPage() {
        nextPage = null
    }

    fun refresh() {
        nextPage = 2
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        null -> LOADING
        else -> ITEM
    }

    class LoadingVH(view: View) : RecyclerView.ViewHolder(view)
    class NoResultVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(@DrawableRes drawableRes: Int? = null, @StringRes stringRes: Int? = null) {
            with(itemView) {
                if (drawableRes == null) {
                    ivError.makeInvisible(true)
                } else {
                    ivError.setImageDrawable(context.getDrawable(drawableRes))
                }
                if (stringRes == null) {
                    tvError.makeInvisible(true)
                } else {
                    tvError.text = context.getString(stringRes)
                }
            }
        }
    }

    companion object {
        const val ITEMS_VISIBILITY_THRESHOLD = 10
        const val ITEM = 0
        const val LOADING = 1


        fun <T> getDiffCallback(areContentsSame: (old: T, new: T) -> Boolean) =
            object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

                override fun areContentsTheSame(oldItem: T, newItem: T) = areContentsSame(oldItem, newItem)
            }
    }
}

typealias VH = RecyclerView.ViewHolder
typealias VhBuilder = (view: View, viewType: Int) -> VH
typealias Binder<M> = (holder: VH, item: M) -> Unit

fun View.makeInvisible(makeGone: Boolean = false) {
    visibility = if (makeGone) View.GONE else View.INVISIBLE
}
*/
