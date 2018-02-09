package hotboys69.dat153.whosetweetisthatappthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    TextView tweetView;

    //the username of the author of the current tweet shown
    String correctUserName;

    //the 4 users you need to guess from
    ArrayList<String> usernamesToGuessFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        showNewTweet();

        tweetView = findViewById(R.id.tweetView);
    }


    /**
     * decides whose tweet it will show and also decides which 3 other twitter accounts you can guess from
     */
    public void showNewTweet()
    {
        if(Math.random() > 0.5){
            correctUserName = Data.musicians.get((int) Math.floor(Math.random() * Data.musicians.size()));
        }else{
            correctUserName = Data.users.get((int) Math.floor(Math.random() * Data.users.size()));
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

    }
}
