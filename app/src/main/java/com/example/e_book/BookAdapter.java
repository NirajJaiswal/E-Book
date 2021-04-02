package com.example.e_book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_book.databinding.BookListBinding;
import com.example.e_book.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<Book> books;

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookListBinding bookListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_list, parent, false);
        return new BookViewHolder(bookListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookListBinding.setBook(book);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private BookListBinding bookListBinding;

        public BookViewHolder(@NonNull BookListBinding bookListBinding) {
            super(bookListBinding.getRoot());
            this.bookListBinding = bookListBinding;
            bookListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(books.get(clickedPosition));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setBooks(ArrayList<Book> newBooks) {
       /* this.books = books;
        notifyDataSetChanged();*/
       final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new DiffUtilCallBack(books,newBooks),false);
       books=newBooks;
       result.dispatchUpdatesTo(BookAdapter.this);

    }
}
