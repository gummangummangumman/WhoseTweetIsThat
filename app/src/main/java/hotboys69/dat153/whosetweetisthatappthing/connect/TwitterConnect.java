package hotboys69.dat153.whosetweetisthatappthing.connect;


import android.content.res.Resources;
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

import hotboys69.dat153.whosetweetisthatappthing.util.RandomTweetPicker;
import hotboys69.dat153.whosetweetisthatappthing.util.TweetFilter;
import hotboys69.dat153.whosetweetisthatappthing.view.GameActivity;
import retrofit2.Call;

public class TwitterConnect {

    /**
     * @param username the @ of the user
     * @param view GameActivity
     */
    public static void setUserInformation(final String username, GameActivity view)
    {
        final WeakReference<GameActivity> callback = new WeakReference<GameActivity>(view);
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, username, null, null, null, null, true, null, false);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                if(result.data.size() == 0)
                {
                    Log.e("NO_TWEETS", "No tweets from user @" + username);
                    callback.get().setFailed();
                    return;
                }
                else if(result.data.get(0).user.screenName.equals(callback.get().correctUserName))
                {
                    try{
                        String randomTweet = RandomTweetPicker.getRandomTweet(result.data);
                        callback.get().setTweet(randomTweet);
                    } catch (Resources.NotFoundException e){
                        e.printStackTrace();
                        callback.get().setFailed();
                        return;
                    }
                }
                callback.get().setUserInformation(result.data.get(0).user.name, result.data.get(0).user.profileImageUrl, result.data.get(0).user.screenName);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e("TWITTER_CONNECT_ERROR", exception.getMessage());
                exception.printStackTrace();
                callback.get().setFailed();
            }
        });
    }

}
