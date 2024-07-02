package com.example.dipractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dao: StudentDao,
    private val repository: SearchRepository
) : ViewModel() {

    private val _studentList = MutableLiveData<List<Student>>()
    val studentList : LiveData<List<Student>> get() = _studentList

    fun getStudentList() {
        CoroutineScope(Dispatchers.IO).launch {
            launch {
                for(i in 0..100){
                    val data =  Student(i+1, "name${i+1}")
                    dao.insertStudent(data)
                }
                val list = dao.getAllStudents()
                _studentList.postValue(list)
            }
        }
    }

    fun getSearchImage(query: String){
        viewModelScope.launch {
            val response = repository.getSearchImage(query,"kor", 1, 1)
        }
    }

    fun getStudentResources() : Flow<PagingData<Student>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {StudentPagingResource(dao)}
        ).flow
        return pager.cachedIn(viewModelScope)
    }
}

