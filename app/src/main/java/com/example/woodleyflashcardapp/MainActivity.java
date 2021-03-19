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

import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    private Object AddCardActivity;
    FlashcardDatabase flashcardDatabase;
    // variable to hold a list of flashcards
    List<Flashcard> allFlashcards;
    //variable to track cards
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        if (allFlashcards != null && allFlashcards.size()>0){
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());

        }


        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);
        TextView answers1 = findViewById(R.id.answer1);
        TextView answers2 = findViewById(R.id.answer2);
        TextView answers3 = findViewById(R.id.answer3);
        ImageView eyeofff = findViewById(R.id.toggle_choices_visibility);
        ImageView eyeonn = findViewById(R.id.toggle_choices_Invisibility);
        ImageView addButton = findViewById(R.id.add);
        ImageView eraserButton = findViewById(R.id.eraser);
        ImageView editButton = findViewById(R.id.editbutton);
        ImageView nextButton = findViewById(R.id.next);


       // Eye toggle using boolean
        // toggle for eye off make answers invisible
        eyeofff.setOnClickListener(new View.OnClickListener() {
            boolean isShowingAnswers = true;
            public void onClick(View v) {
                if (isShowingAnswers) {
                    eyeofff.setImageResource(R.drawable.eyeoff);
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
                    eyeonn.setVisibility(View.VISIBLE);
                    eyeofff.setVisibility(View.INVISIBLE);
                    isShowingAnswers = true;

                }
            }
        });
        // toggle for eye onn make answers visible
        eyeonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingAnswers) {
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
                    eyeonn.setVisibility(View.VISIBLE);
                    eyeofff.setVisibility(View.INVISIBLE);
                    isShowingAnswers = true;

                }

            }
        });

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

        // Create eraser for current question and answer
        eraserButton.setOnClickListener(new View.OnClickListener() {
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

        // Create edit button for currently display question and answer
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                //get what ever is in question and answer to the edit text in activity2
                String question =  ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                String answer = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();
                String answerChoice1 = ((TextView) findViewById(R.id.answer3)).getText().toString();
                String wrongAnswerChoice1 = ((TextView) findViewById(R.id.answer1)).getText().toString();
                String wrongAnswerChoice2 = ((TextView) findViewById(R.id.answer2)).getText().toString();
                intent.putExtra("stringKey1",question);
                intent.putExtra("stringKey2",answer);
                intent.putExtra("stringKey3",answerChoice1);
                intent.putExtra("stringKey4",wrongAnswerChoice1);
                intent.putExtra("stringKey5",wrongAnswerChoice2);
                MainActivity.this.startActivityForResult(intent,60);

            }
        });

        // initialize data base
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());

        // next button for saved flash card
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allFlashcards.size() == 0)
                    return;
                currentCardDisplayedIndex++;

                if (currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(findViewById(R.id.next),"You've reached the end of the cards, going back to start.", Snackbar.LENGTH_SHORT).show();
                    currentCardDisplayedIndex=0;
                }
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());

            }
        });

    }
    // display new user Question and answer to flash card
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 60 && resultCode == RESULT_OK ) {
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");
            String answerChoice1 = data.getExtras().getString("string3");
            String wrongAnswerChoice1 = data.getExtras().getString("string4");
            String wrongAnswerChoice2 = data.getExtras().getString("string5");
            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(string2);
            ((TextView) findViewById(R.id.answer3)).setText(answerChoice1);
            ((TextView) findViewById(R.id.answer1)).setText(wrongAnswerChoice1);
            ((TextView) findViewById(R.id.answer2)).setText(wrongAnswerChoice2 );
            // snack bar
            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card successfully created",
                    Snackbar.LENGTH_SHORT)
                    .show();
            // save a flash card
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }


}

