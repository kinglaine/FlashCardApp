package com.example.woodleyflashcardapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //random method to display cards in random using the  index variable currentCardDisplayedIndex
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
    boolean isShowingAnswers = true;
    private Object AddCardActivity;
    FlashcardDatabase flashcardDatabase;
    // variable to hold a list of flashcards
    List<Flashcard> allFlashcards;
    //variable to track cards
    int currentCardDisplayedIndex = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        TextView emptyText1 = findViewById(R.id.emptyText1);
        TextView emptyText2 = findViewById(R.id.emptyText2);
        ImageView emptyCard = findViewById(R.id.emptyCard);

        answers1.setVisibility(View.INVISIBLE);
        answers2.setVisibility(View.INVISIBLE);
        answers3.setVisibility(View.INVISIBLE);




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
                //animation to reveal answer
                View answerSideView = findViewById(R.id.flashcard_answer);
                View questionSideView = findViewById(R.id.flashcard_question);
                //get the center for the clipping circle
                int cx = answerSideView.getWidth()/2;
                int cy = answerSideView.getHeight()/2;
                //get the final radius for the clipping circle
                float finalRadius =(float) Math.hypot(cx,cy);
                //Create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView,cx,cy,0f,finalRadius);
                //hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(1000);
                anim.start();

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
                overridePendingTransition(R.anim.right_in,R.anim.left_out);


            }

        });

        // Create delete button to delete flashCards
        eraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;

                currentCardDisplayedIndex++;}
                allFlashcards = flashcardDatabase.getAllCards();
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_answer)).getText().toString());

                /*if (allFlashcards.size() == 0)
                    return;
                    currentCardDisplayedIndex++;*/
                if (allFlashcards.size() > 0) {
                    questionTextView.setVisibility(View.VISIBLE);
                    answerTextView.setVisibility(View.INVISIBLE);
                    answers1.setVisibility(View.INVISIBLE);
                    answers2.setVisibility(View.INVISIBLE);
                    answers3.setVisibility(View.INVISIBLE);

                    eraserButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    eyeofff.setVisibility(View.VISIBLE);
                    eyeonn.setVisibility(View.VISIBLE);
                    editButton.setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());

                } else if (allFlashcards.size()==0) {
                    allFlashcards = flashcardDatabase.getAllCards();
                    questionTextView.setVisibility(View.INVISIBLE);
                    answerTextView.setVisibility(View.INVISIBLE);
                    answers1.setVisibility(View.INVISIBLE);
                    answers2.setVisibility(View.INVISIBLE);
                    answers3.setVisibility(View.INVISIBLE);

                    eraserButton.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);
                    eyeofff.setVisibility(View.INVISIBLE);
                    eyeonn.setVisibility(View.INVISIBLE);
                    editButton.setVisibility(View.INVISIBLE);
                    emptyCard.setVisibility(View.VISIBLE);
                    emptyText1.setVisibility(View.VISIBLE);
                    emptyText2.setVisibility(View.VISIBLE);
                }

                //Use delete method from database

                /*flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_answer)).getText().toString());
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.answer1)).getText().toString());
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.answer2)).getText().toString());
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.answer3)).getText().toString());*/


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
                // add animation when going to next question
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(),R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(),R.anim.right_in);
                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });
                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_question).startAnimation(rightInAnim);


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

