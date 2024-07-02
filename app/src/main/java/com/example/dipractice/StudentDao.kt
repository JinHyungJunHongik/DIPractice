package com.example.dipractice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)  // INSERT, key 충돌이 나면 새 데이터로 교체
    suspend fun insertStudent(student: Student)

    @Query("SELECT * FROM student_table")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM student_table WHERE name = :sname")
    suspend fun getStudentByName(sname: String): List<Student>
    @Delete
    suspend fun deleteStudent(student: Student)
}