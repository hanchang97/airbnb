package com.team16.airbnb.di

import com.team16.airbnb.common.DateChoiceListener
import com.team16.airbnb.ui.calendar.CalendarFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ListenerModule {

    @Singleton
    @Binds
    abstract fun bindDateChoiceListenerModule(
        calendarFragment: CalendarFragment
    ): DateChoiceListener
}