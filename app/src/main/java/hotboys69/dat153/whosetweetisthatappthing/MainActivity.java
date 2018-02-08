package hotboys69.dat153.whosetweetisthatappthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonConnect.connect(this).execute("pkfsf");
        //ArrayList<String> tweets = JsonConnect.getAllTweets(Data.users.get(0));
    }

    public void setTweets(ArrayList<String> tweets)
    {
        for(String s:tweets){
            Log.w("mainActivity", s);
        }
    }
}
