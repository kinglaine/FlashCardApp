package com.example.woodleyflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card2);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this,MainActivity.class);
                AddCardActivity.this.startActivityForResult(intent,60);
            }
        });
        Intent data = new Intent();
        data.putExtra("Question", "Enter a question");
        data.putExtra("Answer","Answer to question");
        String Question = getIntent().getStringExtra("Question");
        String Answer = getIntent().getStringExtra("Answer");
        EditText Edit = (EditText) findViewById(R.id.AddQuestion);
        //String value = Edit.getText().toString;
    }
}