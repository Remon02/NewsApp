package com.loc.newsapp.domain.useCases.app_entry

import com.loc.newsapp.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger:LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.SaveAppEntry()
    }
}