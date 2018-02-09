package hotboys69.dat153.whosetweetisthatappthing;

import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import retrofit2.Call;

/**
 * Created by GuMMaN on 09.02.2018.
 */

public class TwitterConnect {

    public MainActivity activity;

    public static String getRandomTweet(String username)
    {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, username, null, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                String randomTweet = result.data.get((int) Math.floor(Math.random() * result.data.size())).text;
                Log.w("username", result.data.get(0).user.name);
                Log.w("userAvatar", result.data.get(0).user.profileImageUrl);
                Log.w("randomTweet", randomTweet);
                Log.w("howManyTweets", String.valueOf(result.data.size()));
            }

            public void failure(TwitterException exception) {
                Log.w("fail", "it failz");
            }
        });
        return "lol";
    }

}
