package hotboys69.dat153.whosetweetisthatappthing.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.twitter.sdk.android.core.Twitter;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.connect.ImageDownloader;
import hotboys69.dat153.whosetweetisthatappthing.connect.TwitterConnect;
import hotboys69.dat153.whosetweetisthatappthing.data.Settings;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;
import hotboys69.dat153.whosetweetisthatappthing.util.TweeterRandomiser;
import hotboys69.dat153.whosetweetisthatappthing.viewmodel.TweeterViewModel;

public class GameActivity extends AppCompatActivity {

    //score
    int score = 0;

    //where it's supposed to be
    int where = 0;

    //the text view of the tweet to guess on and the score
    TextView tweetView, scoreView;

    //the 4 buttons etc
    Button button1, button2, button3, button4, nextButton;

    //the username of the author of the current tweet shown
    public String correctUserName;

    //the media player will play sounds on right or wrong guesses
    MediaPlayer successSound, failureSound;

    //animates the tweet view when the user has answered
    ValueAnimator anim;

    //if a tweeter has failed to load
    boolean failed = false;

    TweeterViewModel viewModel;
    List<TweeterCategory> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Twitter.initialize(this);

        viewModel = new ViewModelProvider(this).get(TweeterViewModel.class);
        categories = viewModel.getAllActiveCategories();
        viewModel.getActiveCategoriesLiveData().observe(this, value ->
                categories = viewModel.getAllActiveCategories());


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1 = setOnClick(button1);
        button2 = setOnClick(button2);
        button3 = setOnClick(button3);
        button4 = setOnClick(button4);

        disableAllButtons();

        nextButton = findViewById(R.id.next_button);

        tweetView = findViewById(R.id.tweet_view);
        scoreView = findViewById(R.id.score_view);

        scoreView.setText(getString(R.string.score, score));

        if (Settings.soundEnabled) {
            successSound = MediaPlayer.create(this, R.raw.success);
            failureSound = MediaPlayer.create(this, R.raw.incorrect);
        }


        // Showing a tweet after one second
        new Handler().postDelayed(this::showNewTweet, 1000);
    }


    /**
     * Decides whose tweet it will show and also decides which 3 other twitter accounts
     * you can guess from
     */
    public void showNewTweet()
    {
        failed = false;
        resetUI();

        //generate 4 random tweeters
        List<String> tweetersToGuessFrom = TweeterRandomiser.getRandomTweeters(categories);

        //choose one as the correct one
        correctUserName = TweeterRandomiser.getRandomTweeter(tweetersToGuessFrom);

        //get all the names and images for the 4 tweeters to guess from
        //it will also show the tweet from the right tweeter
        for (String s : tweetersToGuessFrom) {
            TwitterConnect.setUserInformation(s, this);
        }
    }

    /**
     * should be called when the main tweet failed
     *
     * @param offendingUsername The username of the user who could not load a tweet,
     *                          if there is one. Null if there was another problem.
     */
    public void setFailed(@Nullable String offendingUsername)
    {
        if (offendingUsername == null) {
            setTweet(getString(R.string.load_tweet_error));
        } else {
            setTweet(getString(R.string.tweet_error, offendingUsername));
        }
        animateTweetView();
        failed = true;
        disableAllButtons();
        tweetView.setOnClickListener(view -> showNewTweet());
        nextButton.setVisibility(View.VISIBLE);
        nextButton.setOnClickListener(view -> showNewTweet());
    }


    /**
     * shows the tweet in the tweetView. Called from TwitterConnect
     */
    public void setTweet(String tweet)
    {
        if (!failed) {
            String withoutLink = tweet.split("http")[0];

            tweetView.setText(withoutLink);
            enableAllButtons();
        }
    }

    /**
     * @param name    the screen name (not the @)
     * @param picture url to the picture
     * @param at      the @ of the user (also called screenName, handle)
     */
    @SuppressLint("SetTextI18n")
    public void setUserInformation(String name, String picture, String at)
    {
        switch (where) {
            case 0:
                button1.setText(name + "\n@" + at);
                new ImageDownloader.DownloadPictureTask(button1).execute(picture);
                where++;
                break;
            case 1:
                button2.setText(name + "\n@" + at);
                new ImageDownloader.DownloadPictureTask(button2).execute(picture);
                where++;
                break;
            case 2:
                button3.setText(name + "\n@" + at);
                new ImageDownloader.DownloadPictureTask(button3).execute(picture);
                where++;
                break;
            case 3:
                button4.setText(name + "\n@" + at);
                new ImageDownloader.DownloadPictureTask(button4).execute(picture);
                where = 0;
                break;
            default:
                Log.e("Error", "that place to put the information does not exist: " + where);
                break;
        }
    }


    /**
     * sets the onclick to a button and returns the button
     */
    public Button setOnClick(Button button)
    {
        final Button newButton = button;
        button.setOnClickListener(view -> {
            String buttonText = newButton.getText().toString();
            if (buttonText.toLowerCase().contains(correctUserName.toLowerCase())) {
                newButton.setBackgroundColor(ContextCompat
                        .getColor(getApplicationContext(), R.color.correctAnswerGreen));
                score++;
                scoreView.setText(getString(R.string.score, score));

                nextButton.setVisibility(View.VISIBLE);
                nextButton.setOnClickListener(v -> showNewTweet());
                tweetView.setOnClickListener(v -> showNewTweet());

                if (Settings.soundEnabled) {
                    successSound.start();
                }

            } else {
                newButton.setBackgroundColor(ContextCompat
                        .getColor(getApplicationContext(), R.color.wrongAnswerRed));
                Button correctButton = getCorrectButton();
                if (correctButton != null)
                    correctButton.setBackgroundColor(ContextCompat
                            .getColor(getApplicationContext(), R.color.correctAnswerGreen));

                nextButton.setVisibility(View.VISIBLE);
                nextButton.setOnClickListener(v -> loseGame());
                tweetView.setOnClickListener(v -> loseGame());

                if (Settings.soundEnabled) {
                    failureSound.start();
                }
            }

            disableAllButtons();
            animateTweetView();
        });
        return newButton;
    }

    /**
     * @return the button with the correct tweeter shown
     */
    public Button getCorrectButton()
    {
        if (button1.getText().toString().toLowerCase().contains(correctUserName.toLowerCase()))
            return button1;
        else if (button2.getText().toString().toLowerCase().contains(correctUserName.toLowerCase()))
            return button2;
        else if (button3.getText().toString().toLowerCase().contains(correctUserName.toLowerCase()))
            return button3;
        else if (button4.getText().toString().toLowerCase().contains(correctUserName.toLowerCase()))
            return button4;
        else {
            Log.e("buttonError", "there is no correct button! username: "
                    + correctUserName);
            return null;
        }
    }


    /**
     * sets all the buttons' colours to the original colour and enables them all.
     * deletes the onClick from the tweetView
     */
    public void resetUI()
    {
        int twitterWhite = ContextCompat
                .getColor(getApplicationContext(), R.color.twitterOfficialWhite);
        button1.setBackgroundColor(twitterWhite);
        button2.setBackgroundColor(twitterWhite);
        button3.setBackgroundColor(twitterWhite);
        button4.setBackgroundColor(twitterWhite);
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button1.setCompoundDrawables(null, null, null, null);
        button2.setCompoundDrawables(null, null, null, null);
        button3.setCompoundDrawables(null, null, null, null);
        button4.setCompoundDrawables(null, null, null, null);
        nextButton.setVisibility(View.INVISIBLE);
        tweetView.setOnClickListener(null);
        tweetView.setText("");
        if (anim != null) {
            anim.end();
            tweetView.setBackgroundColor(Color.WHITE);
        }
    }

    private void enableAllButtons()
    {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
    }

    private void disableAllButtons()
    {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }


    private void loseGame()
    {
        Intent loseIntent = new Intent(this, LoseActivity.class);
        loseIntent.putExtra("score", score);
        this.finish();
        startActivity(loseIntent);
    }

    private void animateTweetView()
    {
        anim = new ValueAnimator();
        anim.setIntValues(Color.WHITE, getResources().getColor(R.color.animateCard));
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(valueAnimator ->
                tweetView.setBackgroundColor((Integer) valueAnimator.getAnimatedValue()));

        anim.setDuration(2000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);

        anim.start();
    }
}
