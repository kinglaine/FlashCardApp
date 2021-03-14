package com.example.woodleyflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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




       /* Eye toggle using boolean
       eyeofff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eyeofff.setImageResource(R.drawable.eyeoff);
                if (isShowingAnswers) {
                    answers1.setVisibility(View.VISIBLE);
                    answers2.setVisibility(View.VISIBLE);
                    answers3.setVisibility(View.VISIBLE);
                    eyeofff.setImageResource(R.drawable.eyeoff);
                    isShowingAnswers= false;

                } else {
                answers1.setVisibility(View.INVISIBLE);
                answers2.setVisibility(View.INVISIBLE);
                answers3.setVisibility(View.INVISIBLE);
                eyeonn.setImageResource(R.drawable.eyeon);
                isShowingAnswers = true;

            }
            }
        });*/
        // toggle for eye off make answer invisible
        eyeofff.setOnClickListener(new View.OnClickListener() {
            boolean isShowingAnswers = true;
            @Override
            public void onClick(View v) {
                eyeofff.setImageResource(R.drawable.eyeoff);
                eyeonn.setImageResource(R.drawable.eyeon);
                eyeofff.setVisibility(View.VISIBLE);
                eyeonn.setVisibility(View.INVISIBLE);
                answers1.setVisibility(View.VISIBLE);
                answers2.setVisibility(View.VISIBLE);
                answers3.setVisibility(View.VISIBLE);
            }
        });

        // toggle for eye on make answer visible
        eyeonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eyeofff.setImageResource(R.drawable.eyeoff);
                eyeonn.setImageResource(R.drawable.eyeon);
                eyeofff.setVisibility(View.VISIBLE);
                eyeonn.setVisibility(View.INVISIBLE);
                answers1.setVisibility(View.INVISIBLE);
                answers2.setVisibility(View.INVISIBLE);
                answers3.setVisibility(View.INVISIBLE);

            }
        });



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
                addButton.setVisibility(View.INVISIBLE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 60 ) {
            String String1 = ((EditText) findViewById(R.id.QuestionActivity2)).getText().toString();
            String String2 = ((EditText) findViewById(R.id.AnswersActivity2)).getText().toString();
            TextView questionView = (TextView) findViewById(R.id.flashcard_question);
            questionView.setText(String1);
            TextView answerView = (TextView) findViewById(R.id.flashcard_answer);
            answerView.setText(String2);
        }
    }

    }

