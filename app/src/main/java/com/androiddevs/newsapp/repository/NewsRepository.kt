package com.androiddevs.newsapp.repository

import com.androiddevs.newsapp.api.RetrofitInstance
import com.androiddevs.newsapp.db.ArticleDatabase
import com.androiddevs.newsapp.models.Article

class NewsRepository (
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) =db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getALLArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)


}