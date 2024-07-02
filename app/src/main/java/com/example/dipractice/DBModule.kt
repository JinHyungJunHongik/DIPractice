package com.example.dipractice

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideStudentDataBase(
        @ApplicationContext context: Context
    ) : StudentDataBase = Room.databaseBuilder(
        context, StudentDataBase::class.java, "school_database"
    ).build()


    @Singleton
    @Provides
    fun provideStudentDao(dataBase: StudentDataBase) : StudentDao = dataBase.getStudentDao()

}