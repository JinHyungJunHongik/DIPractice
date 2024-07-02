package com.example.dipractice

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "student_table")
data class Student (
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    val id: Int,
    val name: String
) : Parcelable