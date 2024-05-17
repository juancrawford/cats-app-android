package com.catsapp.android.presentation.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Slide
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.catsapp.android.R
import com.catsapp.android.databinding.ActivityDetailBinding
import com.catsapp.android.model.Cat
import com.catsapp.android.utils.parcelable
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity for displaying detailed information about a cat.
 *
 * This activity displays detailed information about a cat, including its image, title, and description.
 * It uses shared element transition for smooth transition effects and Glide library for image loading.
 */
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityTransition()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        ViewCompat.setTransitionName(binding.appBar, getString(R.string.transition_toolbar))
        supportPostponeEnterTransition()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val cat = intent.parcelable<Cat>(EXTRA_CAT)!!
        initImageView(cat.url)
        viewModel.loadData(cat)
    }

    /**
     * Called when a menu item is selected.
     *
     * @param item The selected MenuItem.
     * @return true if the menu item is handled successfully, false otherwise.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Initializes the activity transition.
     */
    private fun initActivityTransition() {
        val transition = Slide()
        transition.excludeTarget(android.R.id.statusBarBackground, true)
        window.enterTransition = transition
        window.returnTransition = transition
    }

    /**
     * Initializes the image view with Glide for loading the cat image.
     *
     * @param imageUrl The URL of the cat image.
     */
    private fun initImageView(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl.toUri().buildUpon().scheme("https").build())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }
            })
            .into(binding.image)
    }

    companion object {
        const val EXTRA_CAT = "extra-cat"
    }
}