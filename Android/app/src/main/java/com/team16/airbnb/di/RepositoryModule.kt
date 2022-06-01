package com.team16.airbnb.di

import com.team16.airbnb.data.repository.CalendarRepository
import com.team16.airbnb.data.repository.CalendarRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCalendarRepositoryModule(
        calendarRepositoryImpl: CalendarRepositoryImpl
    ) : CalendarRepository

}