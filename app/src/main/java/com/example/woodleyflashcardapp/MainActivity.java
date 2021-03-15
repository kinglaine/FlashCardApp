package com.example.woodleyflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    private Object AddCardActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);
        TextView answers1 = findViewById(R.id.answer1);
        TextView answers2 = findViewById(R.id.answer2);
        TextView answers3 = findViewById(R.id.answer3);
        ImageView eyeofff = findViewById(R.id.toggle_choices_visibility);
        ImageView eyeonn = findViewById(R.id.toggle_choices_Invisibility);
        ImageView addButton = findViewById(R.id.add);
        ImageView editButton = findViewById(R.id.editOrAnswer);


       // Eye toggle using boolean

        // toggle for eye off make answer invisible
        /*eyeofff.setOnClickListener(new View.OnClickListener() {
            boolean isShowingAnswers = true;
            public void onClick(View v) {
                if (isShowingAnswers) {
                    eyeonn.setVisibility(View.INVISIBLE);
                    eyeofff.setVisibility(View.VISIBLE);
                    answers1.setVisibility(View.VISIBLE);
                    answers2.setVisibility(View.VISIBLE);
                    answers3.setVisibility(View.VISIBLE);
                    isShowingAnswers= false;

                }
                else {
                    eyeonn.setImageResource(R.drawable.eyeon);
                    answers1.setVisibility(View.INVISIBLE);
                    answers2.setVisibility(View.INVISIBLE);
                    answers3.setVisibility(View.INVISIBLE);
                    eyeofff.setVisibility(View.VISIBLE);
                    isShowingAnswers = true;

                }
            }
        });*/

        // Toggle back and forth between question and answer
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);

            }
        });
        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);
            }
        });

        // Change answers background color to verify if true or false
        answers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.my_red_color));

            }
        });
        answers2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.my_red_color));

            }
        });
        answers3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.my_green_color));

            }
        });

        // add button to get to other activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,60);


            }

        });

        // Create edit button for current question and answer
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddCardActivity.class);
                String question =  ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                String answer = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();
                String answer1n1 = ((TextView) findViewById(R.id.answer3)).getText().toString();
                String wrongAnswer1n2 = ((TextView) findViewById(R.id.answer1)).getText().toString();
                String wrongAnswer1n3 = ((TextView) findViewById(R.id.answer2)).getText().toString();

                intent.putExtra("key1",question);
                intent.putExtra("key2",answer);
                MainActivity.this.startActivityForResult(intent,60);
            }
        });

    }
    // display new user Question and answer to flash card
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 60 ) {
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");
            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(string2);

            // snack bar
            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card successfully created",
                    Snackbar.LENGTH_SHORT)
                    .show();

        }
    }

}

