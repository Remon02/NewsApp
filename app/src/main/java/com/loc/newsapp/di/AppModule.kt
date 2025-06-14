package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manger.LocalUserMangerImpl
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manger.LocalUserManger
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.useCases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.useCases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.useCases.news.GetNews
import com.loc.newsapp.domain.useCases.news.NewsUseCases
import com.loc.newsapp.domain.useCases.news.SearchNews
import com.loc.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUSeCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)

    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }

    @Provides
    @Singleton

    fun provideNewsRepoistort(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}