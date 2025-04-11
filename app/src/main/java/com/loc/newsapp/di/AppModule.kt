package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manger.LocalUserMangerImpl
import com.loc.newsapp.domain.manger.LocalUserManger
import com.loc.newsapp.domain.useCases.AppEntryUseCases
import com.loc.newsapp.domain.useCases.ReadAppEntry
import com.loc.newsapp.domain.useCases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ):LocalUserManger = LocalUserMangerImpl(application)

@Provides
@Singleton
fun provideAppEntryUSeCases(
    localUserManger: LocalUserManger
) = AppEntryUseCases(
    readAppEntry = ReadAppEntry(localUserManger),
    saveAppEntry = SaveAppEntry(localUserManger)

)

}