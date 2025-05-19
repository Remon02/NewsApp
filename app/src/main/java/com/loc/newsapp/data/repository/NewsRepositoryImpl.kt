package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

// This class implements the NewsRepository interface from the domain layer
// It is responsible for getting news articles from the internet using paging
class NewsRepositoryImpl(
    private val newsApi: NewsApi // This is the Retrofit API used to fetch data from the internet
) : NewsRepository {

    // This function will be called from the ViewModel to get paginated news as a Flow
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10), // Each page will contain 10 news articles

            // Tell Pager to use NewsPagingSource as the data source for loading pages
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi, // Pass the API instance to the PagingSource
                    sources = sources.joinToString(separator = ",")
                    // Convert the list of sources (like ["cnn", "bbc"]) into a single string ("cnn,bbc")
                    // because the API requires the sources in this format
                )
            }

        ).flow // This returns the result as a Flow, so we can collect it in the UI with Compose
    }
}
