package hotboys69.dat153.whosetweetisthatappthing;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TweeterListFetcher {


    public static class FetchTweeters extends AsyncTask<String, Void, ArrayList<String>[]> {

        public FetchTweeters()
        {
        }

        @Override
        protected ArrayList<String>[] doInBackground(String...voids)
        {
            String url = voids[0];
            ArrayList<String>[] result = new ArrayList[2];
            result[0] = new ArrayList<String>();
            result[1] = new ArrayList<String>();
            boolean hasFoundSplit = false;

            try {
                InputStream file = (InputStream) new URL(url).getContent();
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(line.equals("|")){
                        hasFoundSplit = true;
                    }else{
                        if(!hasFoundSplit){
                            result[0].add(line);
                        }else{
                            result[1].add(line);
                        }
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            } catch(Error e)
            {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<String>[] result)
        {
            Data.musicians = result[0];
            Data.nonmusicians = result[1];
            Data.defaultValues = true;
        }

    }

}
