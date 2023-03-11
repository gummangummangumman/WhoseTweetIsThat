# WhoseTweetIsThat
Your favourite Tweet quiz app for android phones!

Available on [Google play](https://play.google.com/store/apps/details?id=hotboys69.dat153.whosetweetisthatappthing) or [as an apk from my website](https://gumman.one/whosetweetisthat).

### Setup
You need to set up a twitter API key and make a file `app/src/main/res/values/secrets.xml` with the following content, add the consumer key and consumer secret:

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="com.twitter.sdk.android.CONSUMER_KEY" translatable="false"></string>
    <string name="com.twitter.sdk.android.CONSUMER_SECRET" translatable="false"></string>
</resources>
```
