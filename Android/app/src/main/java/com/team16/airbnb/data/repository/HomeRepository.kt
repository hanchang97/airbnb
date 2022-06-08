package com.team16.airbnb.data.repository

import com.team16.airbnb.R
import com.team16.airbnb.data.datasource.HomeDataSource

class HomeRepository(private val dataSource: HomeDataSource) {

    fun getNearInfo() = dataSource.getNearInfo()

    fun getRecommendThem() = dataSource.getRecommendThem()

    fun getHeroInfo() = dataSource.getHeroInfo()
}