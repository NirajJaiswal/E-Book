package com.example.e_book.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.e_book.model.Book;
import com.example.e_book.model.Category;
import com.example.e_book.model.EBookRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private EBookRepository eBookRepository;
    private LiveData<List<Category>> allCategories;


    private LiveData<List<Book>> booksOfSelectedCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        eBookRepository = new EBookRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        allCategories = eBookRepository.getCategories();
        return allCategories;
    }

    public LiveData<List<Book>> getBooksOfSelectedCategory(int categoryId) {
        booksOfSelectedCategory = eBookRepository.getBooks(categoryId);
        return booksOfSelectedCategory;
    }

    public void addNewBook(Book book) {
        eBookRepository.insertBook(book);
    }

    public void deleteBook(Book book) {
        eBookRepository.deleteBook(book);
    }

    public void updateBook(Book book) {
        eBookRepository.updateBook(book);
    }
}
