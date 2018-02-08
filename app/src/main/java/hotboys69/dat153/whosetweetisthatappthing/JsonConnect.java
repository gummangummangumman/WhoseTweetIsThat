package hotboys69.dat153.whosetweetisthatappthing;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by GuMMaN on 07.02.2018.
 */

public class JsonConnect {

    /**
     *
     * @param username the twitter handle
     * @return all the tweets we can get from that user
     */
    public static ArrayList<String> getAllTweets(String username){
        ArrayList<String> tweets = new ArrayList<String>();


        //CONNECTING
        /*
        following this tutorial right hurrr:
        https://youtu.be/Gyaay7OTy-w
         */
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        InputStream inputStream;

        try {
            //URL url = new URL("http://api.twitter.com/1.1/search/tweets.json");
            URL url = new URL("http://ip.jsontest.com/?callback=showMyIP");
            connection = (HttpURLConnection) url.openConnection();
            Log.w("lol", connection.getRequestMethod());
            Log.w("lol", connection.toString());
            Log.w("lol", connection.getResponseMessage());
            Log.w("lol", String.valueOf(connection.getContentLength()));


            InputStream in = new BufferedInputStream(connection.getInputStream());

            Log.w("lul", in.toString());

            reader = new BufferedReader(new InputStreamReader(in));

            Log.w("lmao", reader.toString());

            String line = "";
            while((line = reader.readLine()) != null){
                Log.w("lol", line);
                tweets.add(line);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
                connection.disconnect();

            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tweets;
    }

}
