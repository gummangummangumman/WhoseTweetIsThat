package hotboys69.dat153.whosetweetisthatappthing;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    //score
    int score = 0;

    //where it's supposed to be
    int where = 0;

    //the text view of the tweet to guess on and the score
    TextView tweetView, scoreView;

    //the 4 buttons etc
    Button button1, button2, button3, button4;

    //the username of the author of the current tweet shown
    String correctUserName;

    //the media player will play sounds on right or wrong guesses
    MediaPlayer successSound, failureSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Twitter.initialize(this);


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1 = setOnClick(button1);
        button2 = setOnClick(button2);
        button3 = setOnClick(button3);
        button4 = setOnClick(button4);

        tweetView = findViewById(R.id.tweetView);
        scoreView = findViewById(R.id.scoreView);

        scoreView.setText("Score: " + score);

        if(Settings.soundEnabled){
            successSound = MediaPlayer.create(this, R.raw.success);
            failureSound = MediaPlayer.create(this, R.raw.incorrect);
        }

        showNewTweet();
    }


    /**
     * decides whose tweet it will show and also decides which 3 other twitter accounts you can guess from
     */
    public void showNewTweet()
    {
        resetUI();

        //generate 4 random tweeters
        ArrayList<String> tweetersToGuessFrom = RandomTweeters.getRandomTweeters();

        //choose one as the correct one
        correctUserName = RandomTweeters.getRandomTweeter(tweetersToGuessFrom);

        //get all the names and images for the 4 tweeters to guess from
        //it will also show the tweet from the right tweeter
        for(String s:tweetersToGuessFrom){
            TwitterConnect.setUserInformation(s, this);
        }
    }


    /**
     * shows the tweet in the tweetView. Called from TwitterConnect
     */
    public void setTweet(String tweet){
        String withoutLink = tweet.split("http")[0];

        Log.w("tweet", tweet);

        tweetView.setText(withoutLink);
    }

    /**
     * @param name the screen name (not the @)
     * @param picture url to the picture
     * @param at the @ of the user (also called screenName, handle)
     */
    public void setUserInformation(String name, String picture, String at){
        if(where==0){
            button1.setText(name + "\n@" + at);
            new ImageDownloader.DownloadPictureTask1(this).execute(picture);
            where++;
        }else if(where==1){
            button2.setText(name + "\n@" + at);
            new ImageDownloader.DownloadPictureTask2(this).execute(picture);
            where++;
        }else if(where==2){
            button3.setText(name + "\n@" + at);
            new ImageDownloader.DownloadPictureTask3(this).execute(picture);
            where++;
        }else if(where==3){
            button4.setText(name + "\n@" + at);
            new ImageDownloader.DownloadPictureTask4(this).execute(picture);
            where = 0;
        }else{
            Log.e("Error", "that place to put the information does not exist: " + where);
        }
    }


    /**
     * sets the onclick to a button and returns the button
     */
    public Button setOnClick(Button button){

        final Button newButton = button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = newButton.getText().toString();
                if(buttonText.contains(correctUserName)){
                    newButton.setBackgroundColor(getResources().getColor(R.color.correctAnswerGreen));
                    score++;
                    scoreView.setText("Score: " + score);
                    tweetView.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            showNewTweet();
                        }
                    });

                    if(Settings.soundEnabled){
                        successSound.start();
                    }

                }else{
                    newButton.setBackgroundColor(getResources().getColor(R.color.wrongAnswerRed));
                    Button correctButton = getCorrectButton();
                    if(correctButton!=null)
                        correctButton.setBackgroundColor(getResources().getColor(R.color.correctAnswerGreen));

                    tweetView.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            loseGame();
                        }
                    });

                    if(Settings.soundEnabled){
                        failureSound.start();
                    }
                }

                disableAllButtons();
                animateTweetView();
            }
        });
        return newButton;
    }

    /**
     * @return the button with the correct tweeter shown
     */
    public Button getCorrectButton(){
        if(button1.getText().toString().contains(correctUserName))
            return button1;
        else if(button2.getText().toString().contains(correctUserName))
            return button2;
        else if(button3.getText().toString().contains(correctUserName))
            return button3;
        else if(button4.getText().toString().contains(correctUserName))
            return button4;
        else{
            Log.e("buttonError", "there is no correct button!");
            return null;
        }
    }


    /**
     * sets all the buttons' colours to the original colour and enables them all.
     * deletes the onClick from the tweetView
     */
    public void resetUI(){
        button1.setBackgroundColor(getResources().getColor(R.color.twitterOfficialWhite));
        button2.setBackgroundColor(getResources().getColor(R.color.twitterOfficialWhite));
        button3.setBackgroundColor(getResources().getColor(R.color.twitterOfficialWhite));
        button4.setBackgroundColor(getResources().getColor(R.color.twitterOfficialWhite));
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button1.setCompoundDrawables(null, null, null, null);
        button2.setCompoundDrawables(null, null, null, null);
        button3.setCompoundDrawables(null, null, null, null);
        button4.setCompoundDrawables(null, null, null, null);
        tweetView.setOnClickListener(null);
        tweetView.setText("");
    }

    public void disableAllButtons(){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }


    public void loseGame(){
        Intent loseIntent = new Intent(this, LoseActivity.class);
        loseIntent.putExtra("score", score);
        this.finish();
        startActivity(loseIntent);
    }

    private void animateTweetView(){
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(Color.WHITE, getResources().getColor(R.color.animateCard));
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tweetView.setBackgroundColor((Integer)valueAnimator.getAnimatedValue());
            }
        });

        anim.setDuration(2000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);

        anim.start();
    }
}
