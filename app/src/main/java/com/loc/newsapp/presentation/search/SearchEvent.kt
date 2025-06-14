package com.loc.newsapp.presentation.search

//kol el events eli mmkn t7sl
sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery:String):SearchEvent()

    object SearchNews : SearchEvent()
}