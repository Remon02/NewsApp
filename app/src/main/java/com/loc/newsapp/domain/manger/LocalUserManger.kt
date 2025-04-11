package com.loc.newsapp.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun SaveAppEntry()

    fun readAppEntry(): Flow<Boolean>


}