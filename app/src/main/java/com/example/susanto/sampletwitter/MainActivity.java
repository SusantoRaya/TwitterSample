package com.example.susanto.sampletwitter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends ActionBarActivity {

    //public TextView TweetList;
    public final static String EXTRA_MESSAGE = "com.example.sampletwitter.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchTwitter(View view){

        Intent intent = new Intent(this, DisplayTwitterActivity.class);
        EditText search_tweet_text = (EditText) findViewById(R.id.tweet_search);
        String searchString = search_tweet_text.getText().toString();

        if(searchString.length() > 0){
            intent.putExtra(EXTRA_MESSAGE, searchString);
            startActivity(intent);
        }

    }


}
