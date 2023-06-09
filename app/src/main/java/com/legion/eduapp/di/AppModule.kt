package com.legion.eduapp.di

import android.app.Application
import androidx.room.Room
import com.legion.eduapp.data.local.FinanceManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFinanceManagerDatabase(app: Application): FinanceManagerDatabase {
        return Room.databaseBuilder(
            app,
            FinanceManagerDatabase::class.java,
            FinanceManagerDatabase.DATABASE_NAME,
        ).build()
    }
}