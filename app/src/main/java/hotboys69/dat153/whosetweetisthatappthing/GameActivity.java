package hotboys69.dat153.whosetweetisthatappthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    //where it's supposed to be
    int where = 0;

    //the text view of the tweet to guess on
    TextView tweetView;

    //the textview of the 4 possible usernames to guess from
    TextView userName1, userName2, userName3, userName4;

    //the textview of the 4 possible @'s to guess from
    TextView userAt1, userAt2, userAt3, userAt4;

    //the pictureview of the 4 possible userpics to guess from
    ImageView image1, image2, image3, image4;

    //the 4 buttons etc
    Button button1, button2, button3, button4;

    //the username of the author of the current tweet shown
    String correctUserName;

    //the 4 users you need to guess from
    ArrayList<String> usernamesToGuessFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Twitter.initialize(this);


        String lol = "sssss";
        lol.contains("ss");

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = button1.getText().toString();
                if(buttonText.contains(correctUserName)){
                    button1.setBackgroundColor(getResources().getColor(R.color.correctAnswerGreen));
                    Toast.makeText(GameActivity.this, "CORRECT :D", Toast.LENGTH_SHORT).show();
                }else{
                    button1.setBackgroundColor(getResources().getColor(R.color.wrongAnswerRed));
                    Toast.makeText(GameActivity.this, "Wrong, dude", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = button2.getText().toString();
                if(buttonText.contains(correctUserName)){
                    button2.setBackgroundColor(getResources().getColor(R.color.correctAnswerGreen));
                    Toast.makeText(GameActivity.this, "CORRECT :D", Toast.LENGTH_SHORT).show();
                }else{
                    button2.setEnabled(false);
                    Toast.makeText(GameActivity.this, "Wrong, dude", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = button3.getText().toString();
                if(buttonText.contains(correctUserName)){
                    button3.setBackgroundColor(getResources().getColor(R.color.correctAnswerGreen));
                    Toast.makeText(GameActivity.this, "CORRECT :D", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "Wrong, dude", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = button4.getText().toString();
                if(buttonText.contains(correctUserName)){
                    Toast.makeText(GameActivity.this, "CORRECT :D", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "Wrong, dude", Toast.LENGTH_SHORT).show();
                }
            }
        });



        tweetView = findViewById(R.id.tweetView);

        showNewTweet();
    }


    /**
     * decides whose tweet it will show and also decides which 3 other twitter accounts you can guess from
     */
    public void showNewTweet()
    {
        //generate 4 random tweeters
        ArrayList<String> tweetersToGuessFrom = RandomTweeters.getRandomTweeters();

        //choose one as the correct one
        correctUserName = RandomTweeters.getRandomTweeter(tweetersToGuessFrom);

        //get all the names and images for the 4 tweeters to guess from
        for(String s:tweetersToGuessFrom){
            TwitterConnect.setUserInformation(s, this);
        }

        //get the correct tweet
        TwitterConnect.getRandomTweet(correctUserName, this);
    }


    /**
     * shows the tweet in the tweetView
     */
    public void setTweet(String tweet){
        tweetView.setText(tweet);
    }

    /**
     * @param name the screen name (not the @)
     * @param picture url to the picture
     * @param at the @ of the user (also called screenName, handle)
     */
    public void setUserInformation(String name, String picture, String at){

        Log.w("where", String.valueOf(where));


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
            where++;
        }else{
            Log.e("Error", "that place to put the information does not exist: " + where);
        }
    }
}
