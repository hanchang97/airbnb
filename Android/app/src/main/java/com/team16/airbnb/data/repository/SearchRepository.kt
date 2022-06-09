package com.team16.airbnb.data.repository

import com.team16.airbnb.data.datasource.SearchDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val dataSource: SearchDataSource){
    fun getSearchAreaList(address: String) = dataSource.getSearchAreaList(address)
}