package com.example.e_book.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EBookRepository {
    private CategoryDao categoryDao;
    private BookDao bookDao;
    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;

    public EBookRepository(Application application) {
        BookDataBase bookDataBase = BookDataBase.getInstance(application);
        categoryDao = bookDataBase.categoryDao();
        bookDao = bookDataBase.bookDao();
    }

    public LiveData<List<Category>> getCategories() {
        categories = categoryDao.getAllCategories();
        return categories;
    }

    public LiveData<List<Book>> getBooks(int categoryId) {
        books = bookDao.getBooks(categoryId);
        return books;
    }

    public void insertCategory(Category category) {
        new InsertCategoryAsyncTak(categoryDao).execute(category);
    }

    public void insertBook(Book book) {
        new InsertBookAsyncTask(bookDao).execute(book);
    }

    public void deleteCategory(Category category) {
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }

    public void deleteBook(Book book) {
        new DeleteBookAsyncTask(bookDao).execute(book);
    }

    public void updateCategory(Category category) {
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }

    public void updateBook(Book book) {
        new UpdateBookAsyncTask(bookDao).execute(book);
    }


    private static class InsertCategoryAsyncTak extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public InsertCategoryAsyncTak(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public InsertBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insert(books[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public DeleteCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.delete(categories[0]);
            return null;
        }
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public DeleteBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.delete(books[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public UpdateCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }

    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public UpdateBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }


        @Override
        protected Void doInBackground(Book... books) {
            bookDao.update(books[0]);
            return null;
        }
    }
}
