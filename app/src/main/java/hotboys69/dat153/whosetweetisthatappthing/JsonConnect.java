package hotboys69.dat153.whosetweetisthatappthing;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by GuMMaN on 07.02.2018.
 */

public class JsonConnect {

    /**
     * @param username the twitter handle
     * @return all the tweets we can get from that user
     */
    public static ArrayList<String> getAllTweets(String username) {
        ArrayList<String> tweets = new ArrayList<String>();


        //CONNECTING
        /*
        following this tutorial right hurrr:
        https://youtu.be/Gyaay7OTy-w
         */
        HttpURLConnection connection = null;

        BufferedInputStream in = null;
        BufferedReader reader = null;

        try {
            //URL url = new URL("http://api.twitter.com/1.1/search/tweets.json");
            URL url = new URL("http://ip.jsontest.com/?callback=showMyIP");
            connection = (HttpURLConnection) url.openConnection();
            Log.w("lol", connection.toString());


            in = new BufferedInputStream(connection.getInputStream());

            Log.w("lul", in.toString());

            reader = new BufferedReader(new InputStreamReader(in));

            Log.w("lmao", reader.toString());

            String line = "";
            while ((line = reader.readLine()) != null) {
                Log.w("line:", line);
                tweets.add(line);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Log.w("finally", "finally!!");

            if (connection != null)
                connection.disconnect();

            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tweets;
    }


    public static class connect extends AsyncTask<String, Void, ArrayList<String>> {
        WeakReference<MainActivity> callback;

        public connect(MainActivity view){
            callback = new WeakReference<MainActivity>(view);
        }
        @Override
        protected ArrayList<String> doInBackground(String... voids) {

            ArrayList<String> tweets = new ArrayList<String>();

            String username = voids[0];
            URL url = null;
            HttpURLConnection connection = null;
            BufferedInputStream in = null;
            BufferedReader reader = null;
            try {
                url = new URL("http://api.twitter.com/1.1/search/tweets.json");
                connection = (HttpURLConnection) url.openConnection();

                in = new BufferedInputStream(connection.getInputStream());

                reader = new BufferedReader(new InputStreamReader(in));

                String line = "";
                while ((line = reader.readLine()) != null) {
                    tweets.add(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                connection.disconnect();
            }
            return tweets;
        }

        @Override
        protected void onPostExecute(ArrayList<String> tweets) {
            callback.get().setTweets(tweets);
        }


    }
}
