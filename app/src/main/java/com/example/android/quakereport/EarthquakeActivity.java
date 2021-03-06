/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create array "earthquakes" of Earthquake objects, and populate with data
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        // Create ListView object and attach it to the ListView in earthquake_activity.xml
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create EarthquakeAdapter object and pass in "earthquakes" array
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }

    public void openWebPage(View view) {

        // Get parent LinearLayout of the item that was clicked in ListView
        LinearLayout location = (LinearLayout) view;
        // Find the invisible TextView that stores the earthquake URL
        TextView secretView = (TextView) location.getChildAt(0);
        // Get the URL from the TextView and put it in urlName
        String urlName = secretView.getText().toString();

        // Parse the URL and pass it off to the intent
        Uri webpage = Uri.parse(urlName);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}

