package com.example.susanto.sampletwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class DisplayTwitterActivity extends Activity {

    private static String consumer_key = "123";
    private static String consumer_secret = "123";
    private static String access_token = "123";
    private static String access_token_secret = "123";

    Twitter _twitter;
    private String term;
    ArrayList<String> statusTexts = new ArrayList<String>();
    ListView tweetView = (ListView) findViewById(R.id.list_tweets);\

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // get term search
        // oauth connect to twitter
        // display with adapter

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_twitter);

        Intent intent = getIntent();
        term = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        _twitter = getTwitterAccess();


    }

    private Twitter getTwitterAccess(){
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(consumer_key);
        builder.setOAuthConsumerSecret(consumer_secret);
        builder.setOAuthAccessToken(access_token);
        builder.setOAuthAccessTokenSecret(access_token_secret);
        return new TwitterFactory(builder.build()).getInstance();
    }

    private class getResultTwitter extends AsyncTask<Void, Void,ArrayList<String>>{

        protected ArrayList<String> doInBackground(Void... params){

            List<Status> statuses = new ArrayList<Status>();
            Query query = new Query(term);
            QueryResult result = _twitter.search(query); //because no permission from oauth???
            for (Status status : result.getTweets()) {
                statusTexts.add( status.getText());
            }

            return statusTexts;
        }

        protected void onPostExecute(ArrayList<String> status) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayTwitterActivity.this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, status);

            tweetView.setAdapter(adapter);
        }

    }


}
