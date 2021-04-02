package com.example.e_book.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao
{
    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);

    @Query("SELECT * FROM "+TablesName.BookTableName)
    LiveData<List<Book>>getAllBooks();

    @Query("SELECT * from "+TablesName.BookTableName+ " WHERE category_id==:categoryId ")
    LiveData<List<Book>>getBooks(int categoryId);
}
