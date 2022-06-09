package com.team16.airbnb.data.repository

import com.team16.airbnb.data.datasource.HomeDataSource
import com.team16.airbnb.data.dto.search.toSearchData
import com.team16.airbnb.data.model.SearchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val dataSource: HomeDataSource): BaseHomeRepository() {

    override fun getRecommendTheme() = getInfo(dataSource.getRecommendTheme())

    override fun getHeroInfo() = getInfo(dataSource.getHeroInfo())

    override fun getNearList() = getInfo(dataSource.getNearList())

    override fun getSearchList(search: String) = flow {
        dataSource.getSearchList(search).map { dto ->
            dto.toSearchData()
        }.collect { data ->
            emit(data)
        }
    }

}