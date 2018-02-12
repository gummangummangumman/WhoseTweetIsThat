package hotboys69.dat153.whosetweetisthatappthing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import android.net.http.*;

import com.squareup.picasso.Downloader;

import java.lang.ref.WeakReference;
import java.net.URL;


/**
 * Created by GuMMaN on 12.02.2018.
 */

public class ImageDownloader {

    /**
     *
     * @param url the url to the profile picture on twitter
     * @return the bitmap of that dang picture
     */
    public static Drawable downloadPicture(String url) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "lol");
            return d;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static class DownloadPictureTask1 extends AsyncTask<String, Void, Drawable> {

        WeakReference<GameActivity> callback;

        public DownloadPictureTask1(GameActivity view){
            callback = new WeakReference<GameActivity>(view);
        }

        @Override
        protected Drawable doInBackground(String...voids) {

            String url = voids[0];

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "lol");
                return d;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            callback.get().image1.setImageDrawable(result);
        }
    }

    public static class DownloadPictureTask2 extends AsyncTask<String, Void, Drawable> {

        WeakReference<GameActivity> callback;

        public DownloadPictureTask2(GameActivity view){
            callback = new WeakReference<GameActivity>(view);
        }

        @Override
        protected Drawable doInBackground(String...voids) {

            String url = voids[0];

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "lol");
                return d;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            callback.get().image2.setImageDrawable(result);
        }
    }

    public static class DownloadPictureTask3 extends AsyncTask<String, Void, Drawable> {

        WeakReference<GameActivity> callback;

        public DownloadPictureTask3(GameActivity view){
            callback = new WeakReference<GameActivity>(view);
        }

        @Override
        protected Drawable doInBackground(String...voids) {

            String url = voids[0];

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "lol");
                return d;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            callback.get().image3.setImageDrawable(result);
        }
    }

    public static class DownloadPictureTask4 extends AsyncTask<String, Void, Drawable> {

        WeakReference<GameActivity> callback;

        public DownloadPictureTask4(GameActivity view){
            callback = new WeakReference<GameActivity>(view);
        }

        @Override
        protected Drawable doInBackground(String...voids) {

            String url = voids[0];

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "lol");
                return d;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            callback.get().image4.setImageDrawable(result);
        }
    }
}
