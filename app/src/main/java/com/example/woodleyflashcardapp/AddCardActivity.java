package com.example.woodleyflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
                //add toast (message)  when a requirement is not met
                if(question.equals("") || answer.equals("")) {
                    Toast fooToast = Toast.makeText(AddCardActivity.this, "Must fill all fields",
                            Toast.LENGTH_SHORT);
                    fooToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    fooToast.show();
                    return;
                }



                // putting strings in intent to pass to main.activity
                Intent data = new Intent();
                data.putExtra("string1",question);
                data.putExtra("string2", answer);
                setResult(RESULT_OK,data);
                finish();
            }


        });

        // add user answer to multiple choice (update fields)
        String answerChoice1 = getIntent().getStringExtra("Answer");
        String wrongAnswerChoice1 = getIntent().getStringExtra("wrongAnswer1");
        String wrongAnswerChoice2 = getIntent().getStringExtra("wrongAnswer2");
        // initialize fields
        TextView answer1n1 = findViewById(R.id.AnswersActivity2);
        TextView answer1n2 = findViewById(R.id.AnswersActivity3);
        TextView answer1n3 = findViewById(R.id.AnswersActivity4);
        ((EditText) findViewById(R.id.AnswersActivity2)).setText(answerChoice1);
        ((EditText) findViewById(R.id.AnswersActivity3)).setText(wrongAnswerChoice1);
        ((EditText) findViewById(R.id.AnswersActivity4)).setText(wrongAnswerChoice2 );




    }


}