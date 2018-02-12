package hotboys69.dat153.whosetweetisthatappthing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.twitter.sdk.android.core.Twitter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();
    }


    public void startGame()
    {
        Intent listPageIntent = new Intent(this, GameActivity.class);
        startActivity(listPageIntent);
    }
}
