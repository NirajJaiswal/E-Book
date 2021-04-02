package com.example.e_book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.e_book.databinding.ActivityAddAndEditBinding;
import com.example.e_book.model.Book;

public class AddAndEditActivity extends AppCompatActivity {

    private Book book;
    public static final String BOOK_ID="bookId";
    public static final String BOOK_NAME="bookName";
    public static final String UNIT_PRICE="unitPrice";
    private ActivityAddAndEditBinding activityAddAndEditBinding;
    private AddAndEditActivityClickHandlers addAndEditActivityClickHandlers;
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        book=new Book();
        activityAddAndEditBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_and_edit);
        activityAddAndEditBinding.setBook(book);

        addAndEditActivityClickHandlers = new AddAndEditActivityClickHandlers(this);
        activityAddAndEditBinding.setClickHandler(addAndEditActivityClickHandlers);
        mIntent=getIntent();
        if(mIntent.hasExtra(BOOK_ID)){
            Log.i("BookIdTest"," at 3 id is "+mIntent.getIntExtra(BOOK_ID,0));
            setTitle("Edit Book");
            book.setBookName(mIntent.getStringExtra(BOOK_NAME));
            book.setUnitPrice(mIntent.getStringExtra(UNIT_PRICE));


        }else{
            setTitle("Add New Book");

        }


    }

    public class AddAndEditActivityClickHandlers{
        Context context;

        public AddAndEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if(book.getBookName()==null){
                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_LONG).show();
            } else if (book.getUnitPrice() == null)
            {
                Toast.makeText(context,"Price field cannot be empty",Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra(BOOK_NAME, book.getBookName());
                intent.putExtra(UNIT_PRICE, book.getUnitPrice());
                Log.i("BookIdTest", book.toString());
                setResult(RESULT_OK, intent);
                finish();
                if(mIntent.hasExtra(BOOK_ID))
                {
                    Toast.makeText(context,"Successfully Updated",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(context,"Successfully entered",Toast.LENGTH_LONG).show();
                }

            }


        }
    }
}