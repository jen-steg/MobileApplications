/*
Jennifer Stegina
 */
/*
Jennifer Stegina
 */
package com.example.jenns.memetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BeginnerMemeQuiz extends AppCompatActivity {

    private TextView txtQuestion;
    private ImageView imgQuestion;
    private Button btnAnswer1;
    private Button btnAnswer2;
    private Button btnAnswer3;
    private Button btnAnswer4;
    private Button btnQuit;

    private String correctAnswer;
    private int correctAnswerCount = 0; //set and initialize correct answer counter
    private int questionCount = 1; //questions start at 1

    ArrayList<ArrayList<String>> beginnerQuizArray = new ArrayList<>();

    String beginnerDataArray[][] = {
            // {"Question?", Pic name , "Wrong answer, Wrong answer, Correct answer, Wrong answer}
            {"What type of game genre does this meme pertain to?", "image_beginner_question_armour", "Puzzles", "Sports", "Action/RPG", "Trivia"},
            {"What author's work is this meme referencing?", "image_beginner_question_grumpycat", "Jack Sprat", "Jane Cabrera", "Dr.Seuss", "Thomas Ravenscroft"},
            {"What is the name of this cat?", "image_beginner_question_grumpycattwo", "Mad Cat", "Grumpy Kitty", "Grumpy Cat", "Angry Cat"},
            {"Who is the man in this meme?", "image_beginner_question_charga", "Jay Cutler", "Gary Strydom", "Arnold Schwarzenegger", "Sylvester Stallone"},
            {"Where is this meme from?", "image_beginner_question_key", "Keenon and Kell", "Drake and Josh", "Key and Peele", "That 70's Show"},
            {"What day is this most likely to happen?", "image_beginner_question_alarm", "Sunday", "Saturday", "Monday", "Wednesday"},
            {"What famous talk show host is this meme referencing?", "image_beginner_question_maury", "Steve Wilkos", "Jerry Springer", "Maury Povich", "Pat Sajak"},
            {"Who is the person in this meme?", "image_beginner_question_madea", "Phylicia Rashad", "Halle Berry", "Tyler Perry(Madea)", "Oprah Winfrey"},
            {"How was this situation handled?", "image_beginner_question_chicken", "None of the above", "Genius", "Literally", "Figuratively"},
            {"Which is the slowest browser?", "image_beginner_question_browsers", "Google Chrome", "Firefox", "Internet Explorer", "Safari"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_meme_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        imgQuestion = findViewById(R.id.imgQuestion);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);

        // set the quit button and the listener
        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BeginnerMemeQuiz.this, QuitalitySplashScreen.class));
            }
        }); // end onClickListener for the quit button

        // Creating expertQuizArray from the expertArray
        for (int i = 0; i < beginnerDataArray.length; i++){

            // prepare the new tempArray for the expert questions
            ArrayList<String> tempArray = new ArrayList<>();
            tempArray.add(beginnerDataArray[i][0]); //text for question
            tempArray.add(beginnerDataArray[i][1]); //image name
            tempArray.add(beginnerDataArray[i][2]); //answer choice 1
            tempArray.add(beginnerDataArray[i][3]); //answer choice 2
            tempArray.add(beginnerDataArray[i][4]); //correct answer
            tempArray.add(beginnerDataArray[i][5]); //answer choice 3

            // Add the tempArray to the expertQuizArray
            beginnerQuizArray.add(tempArray);
        }
        showNextQuestion();
    }


    //turning off the back button functionality for the remainder of the quiz app
    @Override
    public void onBackPressed(){
        // back button will not work now
    }

    public void showNextQuestion(){

        // Gen random number
        Random random = new Random();
        int randomNumber = random.nextInt(beginnerQuizArray.size());

        // use random number to pick a quiz set
        ArrayList<String > expertQuiz = beginnerQuizArray.get(randomNumber);

        // set question
        txtQuestion.setText(expertQuiz.get(0));

        // set image
        imgQuestion.setImageResource(getResources().getIdentifier(expertQuiz.get(1), "drawable", getPackageName()));

        // set correct answer
        correctAnswer = expertQuiz.get(4); // predetermined position of correct answer

        // remove text question and image for the question from the quiz and shuffle
        expertQuiz.remove(0); //remove text at index 0
        expertQuiz.remove(0); // remove image now at index 0
        Collections.shuffle(expertQuiz);

        // set choices for the answers
        btnAnswer1.setText(expertQuiz.get(0));
        btnAnswer2.setText(expertQuiz.get(1));
        btnAnswer3.setText(expertQuiz.get(2));
        btnAnswer4.setText(expertQuiz.get(3));

        // remove this question from the expert array
        beginnerQuizArray.remove(randomNumber);

    } // end method for showing the next question

    public void checkAnswer(View view){

        // "hear" the selected answer
        Button btnAnswer = findViewById(view.getId());
        String btnText = btnAnswer.getText().toString();
        String alertText;

        if (btnText.equals(correctAnswer)){
            //correct answer
            alertText = "Fine. You got one.";
            correctAnswerCount++; // count correct answers for the score at the end

        }
        else {
            // wrong answer
            alertText = "Do you know anything at all?";
        }

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (questionCount == 5){
                    // You've asked five questions, quiz over
                    showResult();
                }
                else {
                    // still have questions to ask, add to the counter and send next
                    questionCount++;
                    showNextQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    } // end method for checking the answers

    public void showResult (){

        // declare variables
        String score1 = correctAnswerCount * 20 + " % ";
        String scored0 = "I've never met an Amish person before.";
        String scored1 = "C'mon, not even Facebook memes?!";
        String scored2 = "Go back to Facebook, Boomer";
        String scored3 = "Not bad…for a Gen Z baby…";
        String scored4 = "…an “educated” Gen Z baby.";
        String scored5 = "You're breathtaking!";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Score: ");

        switch (correctAnswerCount){
            case 0: // if zero correct
                builder.setMessage(score1 + "\n\n"  + scored0);
                break;

            case 1: // if one correct
                builder.setMessage(score1 + "\n\n" + scored1);
                break;

            case 2: // if two correct
                builder.setMessage(score1 + "\n\n" + scored2);
                break;

            case 3: // if three correct
                builder.setMessage(score1 + "\n\n" + scored3);
                break;

            case 4: // if four correct
                builder.setMessage(score1 + "\n\n" + scored4);
                break;

            case 5: // if five correct
                builder.setMessage(score1 + "\n\n" + scored5);

                break;
        } // end switch for correct answer count


        builder.setPositiveButton("Meme again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate(); // button press starts the quiz over again
            }
        });
        builder.setNegativeButton("Give up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish(); // button press takes you back to the user name page in app
            }
        });
        builder.show();

    } // end showResult method

} // end class for beginner meme quiz


