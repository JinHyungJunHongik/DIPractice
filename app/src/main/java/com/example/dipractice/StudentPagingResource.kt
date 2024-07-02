package com.example.dipractice

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.lang.Error
import javax.inject.Inject


private const val STARTING_PAGE = 1


class StudentPagingResource @Inject constructor(
    private val dao: StudentDao
) : PagingSource<Int, Student>() {
    override fun getRefreshKey(state: PagingState<Int, Student>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Student> {
       return try {
            val nextPageNumber = params.key ?: STARTING_PAGE
            val response = dao.getAllStudents()
            return LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == STARTING_PAGE) null else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
           LoadResult.Error(e)
       } catch (e: Exception) {
           LoadResult.Error(e)
       }
    }
}

