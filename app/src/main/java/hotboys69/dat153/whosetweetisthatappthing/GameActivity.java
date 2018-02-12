package hotboys69.dat153.whosetweetisthatappthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    //the text view of the tweet to guess on
    TextView tweetView;

    //the textview of the 4 possible usernames to guess from
    TextView userName1, userName2, userName3, userName4;

    //the username of the author of the current tweet shown
    String correctUserName;

    //the 4 users you need to guess from
    ArrayList<String> usernamesToGuessFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Twitter.initialize(this);

        userName1 = findViewById(R.id.userTweet1);
        userName2 = findViewById(R.id.userTweet2);
        userName3 = findViewById(R.id.userTweet3);
        userName4 = findViewById(R.id.userTweet4);

        tweetView = findViewById(R.id.tweetView);

        showNewTweet();
    }


    /**
     * decides whose tweet it will show and also decides which 3 other twitter accounts you can guess from
     */
    public void showNewTweet()
    {
        if(Math.random() > 0.5) //50% chance of musicians
        {
            correctUserName = Data.musicians.get((int) Math.floor(Math.random() * Data.musicians.size()));
        }else{
            correctUserName = Data.users.get((int) Math.floor(Math.random() * Data.users.size()));
        }

        //TODO fix usernamesToGuessFrom
        for(int i=0;i<4;i++){
            TwitterConnect.setUserInformation(Data.users.get(i), this, i);
        }

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
     * @param where 0, 1, 2 or 3 based on which place it's on
     */
    public void setUserInformation(String name, String picture, int where){

        switch (where){
            case 0: userName1.setText(name);
            case 1: userName2.setText(name);
            case 2: userName3.setText(name);
            case 3: userName4.setText(name);
            default: Log.w("error", "that place to put the information does not exist!");
        }
    }
}
