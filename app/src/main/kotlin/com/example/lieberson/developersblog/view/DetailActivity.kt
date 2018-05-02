package com.example.lieberson.developersblog.view

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import com.example.lieberson.developersblog.R
import com.example.lieberson.developersblog.model.Post
import com.example.lieberson.developersblog.util.extension.loadImage
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAnimation()
        setContentView(R.layout.activity_detail)

        val post = intent.getParcelableExtra<Post>("post")

        imageAuthor.loadImage(post?.author?.url)
        textViewDate.text = post?.published
        textViewLabels.text = post?.labels?.toString()
        textViewContent.loadData(post?.content, "text/html", "UTF-8")

    }

    private fun setAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000
            window.sharedElementExitTransition = changeBounds
        }
    }
}
