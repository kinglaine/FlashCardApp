package wood.example.woodleyflashcardapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wood.woodleyflashcardapp.R;
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
    int currentCardDisplayedIndex=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize data base
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        if (allFlashcards != null && allFlashcards.size()>0){
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());

        }

        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);

        ImageView addButton = findViewById(R.id.add);
        ImageView eraserButton = findViewById(R.id.eraser);
        ImageView editButton = findViewById(R.id.editbutton);
        ImageView nextButton = findViewById(R.id.next);


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
                editButton.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(1000);
                anim.start();

            }
        });
        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.VISIBLE);
                editButton.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);
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

                //Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);
                if(allFlashcards.isEmpty()==true) {
                    Toast fooToast = Toast.makeText(MainActivity.this, "Triche is empty",
                            Toast.LENGTH_SHORT);
                    fooToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    fooToast.show();
                    ((TextView) findViewById(R.id.flashcard_question)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer)).setText("");

                    return;
                }
                if (currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(findViewById(R.id.next),"This is Your last card", Snackbar.LENGTH_SHORT).show();
                    currentCardDisplayedIndex=0;
                }

                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());

                allFlashcards = flashcardDatabase.getAllCards();
                if (currentCardDisplayedIndex >= allFlashcards.size()-1||currentCardDisplayedIndex <= allFlashcards.size()) {
                    currentCardDisplayedIndex++;
                    currentCardDisplayedIndex=0;
                    if (allFlashcards.isEmpty()){
                        ((TextView) findViewById(R.id.flashcard_question)).setText("");
                        ((TextView) findViewById(R.id.flashcard_answer)).setText("");

                    }else{

                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());}
                }
                //((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard);}
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

        // Create edit button for currently display question and answer
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                //get what ever is in question and answer to the edit text in activity2
                String question =  ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                String answer = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();

                intent.putExtra("stringKey1",question);
                intent.putExtra("stringKey2",answer);

                MainActivity.this.startActivityForResult(intent,60);
            }
        });


        // next button for saved flash card
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int localSize = allFlashcards.size() - 1;
                if(localSize < 0) localSize = 0;
                currentCardDisplayedIndex = getRandomNumber(0, localSize);
                if(allFlashcards.isEmpty()==true) {
                    Toast fooToast = Toast.makeText(MainActivity.this, "Triche is empty",
                            Toast.LENGTH_SHORT);
                    fooToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    fooToast.show();
                    ((TextView) findViewById(R.id.flashcard_question)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer)).setText("");
                    return;
                }

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

            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(string2);

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

