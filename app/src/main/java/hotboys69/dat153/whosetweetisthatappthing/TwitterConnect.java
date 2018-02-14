package hotboys69.dat153.whosetweetisthatappthing;


import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;

/**
 * Created by GuMMaN on 09.02.2018.
 */

public class TwitterConnect {

    public MainActivity activity;

    public static void getRandomTweet(String username, GameActivity view)
    {
        final WeakReference<GameActivity> callback = new WeakReference<GameActivity>(view);
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, username, null, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                String randomTweet = result.data.get((int) Math.floor(Math.random() * result.data.size())).text;
                Log.w("username", result.data.get(0).user.name);
                Log.w("userAvatar", result.data.get(0).user.profileImageUrlHttps);
                Log.w("randomTweet", randomTweet);
                Log.w("howManyTweets", String.valueOf(result.data.size()));
                callback.get().setTweet(randomTweet);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w("fail", "it failz");
                callback.get().setTweet("You have played too much. Come back in a few minutes to guess  on more tweets :D");
            }
        });
    }

    /**
     *
     * @param username the @ of the user
     * @param view GameActivity
     */
    public static void setUserInformation(String username, GameActivity view) {
        final WeakReference<GameActivity> callback = new WeakReference<GameActivity>(view);
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, username, null, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                callback.get().setUserInformation(result.data.get(0).user.name, result.data.get(0).user.profileImageUrl, result.data.get(0).user.screenName);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w("fail", "it failz");
            }
        });
    }

}
