package com.example.woodleyflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ImageView cancelbutton = findViewById(R.id.cancel);

        // create cancel button to return to activity mainActivity
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this,MainActivity.class);
                AddCardActivity.this.startActivityForResult(intent,60);
            }
        });
        // save button that return to main activity with user question
        ImageView saveUserQuestion = findViewById(R.id.savequestionbutton);

        saveUserQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AddCardActivity.this,MainActivity.class);
                AddCardActivity.this.startActivityForResult(intent,60);
              String question = ((EditText) findViewById(R.id.QuestionActivity2)).getText().toString();
               String answer =  ((EditText) findViewById(R.id.AnswersActivity2)).getText().toString();
                Intent data = new Intent();
                data.putExtra("string1","newQuestion");
                data.putExtra("string2", "newAnswer");
                setResult(RESULT_OK,data);
                finish();
            }
        });


    }
}