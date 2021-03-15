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

        //get data from main activity
        String s1 = getIntent().getStringExtra("Key1");
        String s2 = getIntent().getStringExtra("Key2");

        // create cancel button to return to activity mainActivity
        ImageView cancelbutton = findViewById(R.id.cancel);
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(AddCardActivity.this,MainActivity.class);
                AddCardActivity.this.startActivityForResult(intent,100);
                // end activity
                finish();
            }
        });

        // save button that return to main activity with user question
        ImageView saveUserQuestion = findViewById(R.id.savequestionbutton);
        saveUserQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grabbing text from edit xml
                String question =  ((EditText) findViewById(R.id.QuestionActivity2)).getText().toString();
                String answer = ((EditText) findViewById(R.id.AnswersActivity2)).getText().toString();
                // putting strings in intent to pass to main.activity
                Intent data = new Intent();
                data.putExtra("string1",question);
                data.putExtra("string2", answer);
                setResult(RESULT_OK,data);
                finish();
            }

        });

    }

}