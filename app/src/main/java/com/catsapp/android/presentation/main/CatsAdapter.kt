package com.catsapp.android.presentation.main

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.catsapp.android.R
import com.catsapp.android.databinding.ListItemCatBinding
import com.catsapp.android.model.Cat
import com.catsapp.android.presentation.detail.DetailActivity
import javax.inject.Inject

/**
 * Adapter for displaying a list of cats.
 *
 * This adapter displays a list of cats using data binding and handles item clicks to navigate to
 * the detail screen with transition animation.
 */
open class CatsAdapter @Inject constructor() :
    PagingDataAdapter<CatItemViewModel, CatsAdapter.CatsViewHolder>(differCallback) {

    /**
     * Creates a new CatsViewHolder instance.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The view type.
     * @return A new CatsViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val binding = ListItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatsViewHolder(binding)
    }

    /**
     * Binds the CatsViewHolder with the given item data.
     *
     * @param holder The CatsViewHolder to bind.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.setIsRecyclable(false)
        }
    }

    /**
     * ViewHolder for displaying a cat item.
     *
     * @param binding The view binding instance for the cat item layout.
     */
    inner class CatsViewHolder(val binding: ListItemCatBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the itemViewModel with the view.
         *
         * @param itemViewModel The CatItemViewModel representing the cat item data.
         */
        fun bind(itemViewModel: CatItemViewModel) {
            binding.setVariable(BR.itemViewModel, itemViewModel)
            binding.cvMainContainer.setOnClickListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivityWithTransition(
                        (itemView.context as AppCompatActivity),
                        binding.ivCat,
                        itemViewModel.cat
                    )
                }, 200)
            }
        }

        /**
         * Starts the detail activity with transition animation.
         *
         * @param activity The AppCompatActivity instance.
         * @param image The shared element view for transition animation.
         * @param cat The Cat object representing the clicked cat item.
         */
        private fun startActivityWithTransition(activity: AppCompatActivity, image: View, cat: Cat) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_CAT, cat)
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, image, activity.getString(R.string.transition_toolbar)
            )
            ActivityCompat.startActivity(activity, intent, options.toBundle())
        }
    }

    companion object {
        /**
         * DiffUtil callback for calculating the difference between old and new items.
         */
        val differCallback = object : DiffUtil.ItemCallback<CatItemViewModel>() {
            override fun areItemsTheSame(
                oldItem: CatItemViewModel,
                newItem: CatItemViewModel
            ): Boolean {
                return oldItem.cat.id == oldItem.cat.id
            }

            override fun areContentsTheSame(
                oldItem: CatItemViewModel,
                newItem: CatItemViewModel
            ): Boolean {
                return oldItem.cat == newItem.cat
            }
        }
    }
}
