package com.catsapp.android.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.catsapp.android.databinding.LoadMoreBinding

/**
 * Adapter for handling load more states in a RecyclerView.
 *
 * This adapter handles the load more states in a RecyclerView and provides a view holder
 * for displaying loading progress and retry button in case of loading errors.
 *
 * @param retry A lambda function to retry loading more data.
 */
class LoadMoreAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadMoreAdapter.LoadMoreViewHolder>() {

    /**
     * Creates a new LoadMoreViewHolder instance.
     *
     * @param parent The parent ViewGroup.
     * @param loadState The current load state.
     * @return A new LoadMoreViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadMoreViewHolder {
        val binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadMoreViewHolder(binding, retry)
    }

    /**
     * Binds the LoadMoreViewHolder with the given load state.
     *
     * @param holder The LoadMoreViewHolder to bind.
     * @param loadState The current load state.
     */
    override fun onBindViewHolder(holder: LoadMoreViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }

    /**
     * View holder for load more states in a RecyclerView.
     *
     * @param binding The view binding instance for the load more layout.
     * @param retry A lambda function to retry loading more data.
     */
    inner class LoadMoreViewHolder(
        private val binding: LoadMoreBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadMoreRetry.setOnClickListener { retry() }
        }

        /**
         * Sets the data for the view holder based on the load state.
         *
         * @param state The current load state.
         */
        fun setData(state: LoadState) {
            binding.apply {
                pbLoadMore.isVisible = state is LoadState.Loading
                tvLoadMore.isVisible = state is LoadState.Error
                btnLoadMoreRetry.isVisible = state is LoadState.Error
            }
        }
    }
}