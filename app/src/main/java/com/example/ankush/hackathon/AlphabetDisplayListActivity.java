package com.example.ankush.hackathon;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlphabetDisplayListActivity extends AppCompatActivity {
private alphabetAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_display_list);
        // Find a reference to the {@link ListView} in the layout
        ListView dataListView = (ListView) findViewById(R.id.list);
        final ArrayList<data_with_link> temp= new ArrayList<>();
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new alphabetAdapter(this, new ArrayList<data_with_link>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

       EarthquakeAsyncTask task = new EarthquakeAsyncTask();

        task.execute("https://career.webindia123.com/career/options/asp/alpha_listing.asp");

     //   ArrayList<data_with_link> a= AlphabetOrderDataExtraction.fetchData(("https://career.webindia123.com/career/options/asp/alpha_listing.asp"));
        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        mAdapter = new alphabetAdapter(this, temp );


        dataListView.setAdapter(mAdapter);
      /**
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
       **/
    }





    //assync task

    @SuppressLint("StaticFieldLeak")
    private  class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<data_with_link>> {

        @Override
        protected ArrayList<data_with_link> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return AlphabetOrderDataExtraction.fetchData(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<data_with_link> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }


}
