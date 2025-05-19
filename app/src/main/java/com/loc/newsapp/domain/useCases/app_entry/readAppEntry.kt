package com.loc.newsapp.domain.useCases.app_entry

import com.loc.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger:LocalUserManger
) {

     operator fun invoke():Flow<Boolean>{
         return localUserManger.readAppEntry()
    }
}