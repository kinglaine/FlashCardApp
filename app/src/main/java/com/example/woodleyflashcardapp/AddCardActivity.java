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

        // create cancel button to return to activity mainActivity
        ImageView cancelButton = findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
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
                String answerChoice1 = ((EditText) findViewById(R.id.AnswersActivity2)).getText().toString();
                String wrongAnswerChoice1 = ((EditText) findViewById(R.id.AnswersActivity3)).getText().toString();
                String wrongAnswerChoice2 = ((EditText) findViewById(R.id.AnswersActivity4)).getText().toString();
                //add toast (message)  when a requirements are not met
                if(question.equals("") || answer.equals("")|| answerChoice1.equals("") ||
                        wrongAnswerChoice1.equals("") || wrongAnswerChoice2.equals("")) {
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
                data.putExtra("string3", answerChoice1);
                data.putExtra("string4", wrongAnswerChoice1);
                data.putExtra("string5", wrongAnswerChoice2);
                setResult(RESULT_OK,data);
                finish();
            }


        });
        // add user answer to multiple choice (update fields)
        // set what ever data in question and answer to activity two
        String s1 = getIntent().getStringExtra("stringKey1");
        String s2 = getIntent().getStringExtra("stringKey2");
        String s3 = getIntent().getStringExtra("stringKey3");
        String s4 = getIntent().getStringExtra("stringKey4");
        String s5 = getIntent().getStringExtra("stringKey5");
        ((EditText)findViewById(R.id.QuestionActivity2)).setText(s1);
        ((EditText)findViewById(R.id.AnswersActivity2)).setText(s3);
        ((EditText)findViewById(R.id.AnswersActivity3)).setText(s5);
        ((EditText)findViewById(R.id.AnswersActivity4)).setText(s4);





    }


}