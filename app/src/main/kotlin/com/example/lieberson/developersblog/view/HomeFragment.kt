package com.example.lieberson.developersblog.view


import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView

import com.example.lieberson.developersblog.R
import com.example.lieberson.developersblog.model.Post
import com.example.lieberson.developersblog.util.extension.showProgress
import com.example.lieberson.developersblog.util.extension.showSnackBarError
import com.example.lieberson.developersblog.viewmodel.HomeViewModel
import com.example.lieberson.developersblog.viewmodel.HomeViewModelFactory
import dagger.android.support.AndroidSupportInjection
import android.support.v4.util.Pair
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val list: MutableList<Post> = ArrayList()

    private lateinit var adapter: HomeRecyclerAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        adapter = HomeRecyclerAdapter(list, this::onClick)
        viewModel.getAllPosts()

        setObservers()
    }

    private fun setObservers() {
        viewModel.getResponse().observe(this, Observer {
            showPostsList(it)
        })

        viewModel.getErrorStatus().observe(this, Observer {
            showSnackBarError(it.toString())
        })

        viewModel.getLoadingStatus().observe(this, Observer {
            showProgress(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener { viewModel.getAllPosts() }
    }

    private fun showProgress(b: Boolean?) {
        activity?.showProgress(recyclerView, progress, b == true)
        swipeRefreshLayout?.isRefreshing = false
    }

    private fun showSnackBarError(str: String) {
        activity?.showSnackBarError(recyclerView, str)
        swipeRefreshLayout?.isRefreshing = false
    }

    private fun showPostsList(posts: List<Post>?) {

        withoutData?.visibility = GONE
        if (posts == null || posts.isEmpty()) {
            withoutData?.visibility = VISIBLE
        }

        adapter.update(posts)
        swipeRefreshLayout?.isRefreshing = false
        showProgress(false)
    }


    fun onClick(post: Post, imageView: ImageView) {
        val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, Pair.create(imageView, "image"))

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("post", post)
        activity?.startActivity(intent, options.toBundle())
    }
}


