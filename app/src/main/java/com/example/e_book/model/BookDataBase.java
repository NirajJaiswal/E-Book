package com.example.e_book.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Book.class}, version = 1)
public abstract class BookDataBase extends RoomDatabase {
    public abstract CategoryDao categoryDao();

    public abstract BookDao bookDao();

        public static BookDataBase INSTANCE;

        public static synchronized BookDataBase getInstance(Context context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        BookDataBase.class, "book_database")
                        .fallbackToDestructiveMigration()//changing version number will destroy previous table
                        .addCallback(callback)
                        .build();
            }
            return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(INSTANCE).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategoryDao categoryDao;
        private BookDao bookDao;

        public InitialDataAsyncTask(BookDataBase bookDataBase) {
            categoryDao = bookDataBase.categoryDao();
            bookDao = bookDataBase.bookDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Category category1 = new Category();
            category1.setCategoryName("Text Books");
            category1.setCategoryDescription("Text Book Description");

            Category category2 = new Category();
            category2.setCategoryName("Novel");
            category2.setCategoryDescription("Novel Description");

            categoryDao.insert(category1);
            categoryDao.insert(category2);

            Book book1 = new Book();
            book1.setCategoryId(1);
            book1.setBookName("Physics");
            book1.setUnitPrice("$ 50");

            Book book2 = new Book();
            book2.setCategoryId(1);
            book2.setBookName("Chemistry");
            book2.setUnitPrice("$ 20");

            Book book3 = new Book();
            book3.setCategoryId(1);
            book3.setBookName("Maths");
            book3.setUnitPrice("$ 90");

            Book book4 = new Book();
            book4.setCategoryId(1);
            book4.setBookName("Biology");
            book4.setUnitPrice("$ 20");

            Book book5 = new Book();
            book5.setCategoryId(1);
            book5.setBookName("History");
            book5.setUnitPrice("$ 50");

            Book book6 = new Book();
            book6.setCategoryId(1);
            book6.setBookName("Civics");
            book6.setUnitPrice("$ 50");

            Book book7 = new Book();
            book7.setCategoryId(2);
            book7.setBookName("Sky is pink");
            book7.setUnitPrice("$ 50");

            Book book8 = new Book();
            book8.setCategoryId(2);
            book8.setBookName("Life change");
            book8.setUnitPrice("$ 20");

            Book book9 = new Book();
            book9.setCategoryId(2);
            book9.setBookName("five point some one");
            book9.setUnitPrice("$ 50");

            Book book10 = new Book();
            book10.setCategoryId(2);
            book10.setBookName("Wings of fire");
            book10.setUnitPrice("$ 200");

            Book book11 = new Book();
            book11.setCategoryId(2);
            book11.setBookName("Winner stands alone");
            book11.setUnitPrice("$ 90");

            Book book12 = new Book();
            book12.setCategoryId(2);
            book12.setBookName("Half girlfriend");
            book12.setUnitPrice("$ 10");

            bookDao.insert(book1);
            bookDao.insert(book2);
            bookDao.insert(book3);
            bookDao.insert(book4);
            bookDao.insert(book5);
            bookDao.insert(book6);
            bookDao.insert(book7);
            bookDao.insert(book8);
            bookDao.insert(book9);
            bookDao.insert(book10);
            bookDao.insert(book11);
            bookDao.insert(book12);

            return null;
        }
    }
}
