package com.example.lieberson.developersblog.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.lieberson.developersblog.R
import com.example.lieberson.developersblog.model.Post
import com.example.lieberson.developersblog.util.extension.loadImage

class HomeRecyclerAdapter(private var list: MutableList<Post>,
                          private val onClick: (post: Post, imageView: ImageView) -> Unit) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    private var lastPosition = -1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)

        holder.itemView.setOnClickListener({
            onClick(item, holder.imageView)
        })

        setAnimation(holder.itemView, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > 0) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView: ImageView = view.findViewById(R.id.imageViewLogo)
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewContent: TextView = view.findViewById(R.id.textViewContent)

        fun bind(post: Post) {
            imageView.loadImage(post.author?.image?.url)
            textViewTitle.text = post.title
            textViewContent.text = Html.fromHtml(post.content)
        }
    }

    fun update(items: List<Post>?) {
        this.list.clear()
        if (items != null) {
            this.list.addAll(items)
        }
        notifyDataSetChanged()
    }
}