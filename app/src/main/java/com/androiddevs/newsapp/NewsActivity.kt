package com.androiddevs.newsapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

import com.androiddevs.newsapp.db.ArticleDatabase
import com.androiddevs.newsapp.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.bottomNavigationView
import kotlinx.android.synthetic.main.activity_news.newsNavHostFragment

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory =NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())


    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("are you sure")
        builder.setMessage("are you sure you want to close app?")
        builder.setPositiveButton("yes",
            {dialogInterface,i->finish()})
        builder.setNegativeButton("no",
            {dialogInterface, i ->})
        builder.show()
    }
}
