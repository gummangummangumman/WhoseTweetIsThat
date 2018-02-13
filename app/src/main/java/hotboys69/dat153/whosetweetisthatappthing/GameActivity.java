package hotboys69.dat153.whosetweetisthatappthing;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        /*

        userName1 = findViewById(R.id.userTweet1);
        userName2 = findViewById(R.id.userTweet2);
        userName3 = findViewById(R.id.userTweet3);
        userName4 = findViewById(R.id.userTweet4);

        userAt1 = findViewById(R.id.userAt1);
        userAt2 = findViewById(R.id.userAt2);
        userAt3 = findViewById(R.id.userAt3);
        userAt4 = findViewById(R.id.userAt4);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        */

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);



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
