package com.catsapp.android.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.catsapp.android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main activity of the application.
 *
 * This activity serves as the main entry point of the application. It displays a list of cats
 * using a RecyclerView and observes data changes from the MainViewModel. It also initializes
 * views and adapters for displaying cat data.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var catsAdapter: CatsAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initViews()
    }

    /**
     * Initializes views and adapters for displaying cat data.
     */
    private fun initViews() {
        binding.apply {
            // Observe cat list data changes and submit data to the adapter
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel!!.catsList.collect {
                        catsAdapter.submitData(it)
                    }
                }
            }

            // Observe loading state changes and show/hide progress bar accordingly
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    catsAdapter.loadStateFlow.collect { loadStates ->
                        val state = loadStates.refresh
                        pbProgress.isVisible = state is LoadState.Loading
                    }
                }
            }

            // Initialize RecyclerView with adapter and layout manager
            rvCats.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = catsAdapter.withLoadStateFooter(
                    LoadMoreAdapter {
                        catsAdapter.retry()
                    }
                )
            }
        }
    }
}
