package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context             The current context. Used to inflate the layout file.
     * @param earthquakeArrayList A List of AndroidFlavor objects to display in a list
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakeArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // The second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakeArrayList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // *** LOCATION ***
        // Find the TextView in the list_item.xml layout with earthquake_location
        TextView subLocationTextView = (TextView) listItemView.findViewById(R.id.earthquake_location_1);
        TextView mainLocationTextView = (TextView) listItemView.findViewById(R.id.earthquake_location_2);
        // Get the earthquake location from the current Earthquake object, split it into two parts,
        // and push them out to the TextView's in list_item.xml.
        // For example, "4km SSE of Taron, Papua New Guinea" becomes:
        //      4km SSE of
        //      Taron, Papua New Guinea
        subLocationTextView.setText(splitLocation(currentEarthquake.getLocation())[0]);
        mainLocationTextView.setText(splitLocation(currentEarthquake.getLocation())[1]);

        // Create Date object and pass in (long) date from Earthquake
        Date earthquakeUTCDate = new Date(currentEarthquake.getDate());

        // *** DATE ***
        // Create TextView object and attach it to TextView earthquake_date in list_item.xml
        // Format millisecond time into "MMM DD, yyyy" and push it out to the TextView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.earthquake_date);
        dateTextView.setText(formatDate(earthquakeUTCDate));

        // *** TIME ***
        // Create TextView object and attach it to TextView earthquake_time in list_item.xml
        // Format millisecond time into "HH:MM:SS" and push it out to the TextView
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.earthquake_time);
        timeTextView.setText(formatTime(earthquakeUTCDate));

        // *** MAGNITUDE ***
        // Find the TextView in the list_item.xml layout with the ID list_item_icon
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.earthquake_magnitude);
        // Get the earthquake magnitude from the current Earthquake object, format it to "0.0",
        // and push the text out to the earthquake_magnitude TextView.
        magnitudeView.setText(String.valueOf(formatMagnitude(currentEarthquake.getMagnitude())));

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * This method takes a raw UTC time code and puts it into "MMM DD, yyyy" format
     *
     * @param dateToFormat is a Date object in millisecond time
     * @return is a String of formatted date
     */
    private String formatDate(Date dateToFormat) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateToFormat);
    }

    /**
     * This method takes a raw UTC time code and puts it into "HH:mm:ss a" format
     *
     * @param dateToFormat is a Date object in millisecond time
     * @return is a String of formatted time
     */
    private String formatTime(Date dateToFormat) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss a");
        return timeFormatter.format(dateToFormat);
    }

    /**
     * This method takes a double and puts it into "0.0" format
     *
     * @param magnitudeToFormat is double to be formatted
     * @return is a formatted double
     */
    private String formatMagnitude(double magnitudeToFormat) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitudeToFormat);
    }

    /**
     * This method takes String of the earthquake's location and splits it into two parts:
     * the distance-from substring ("8km NW of "; and the nearest-city substring.  If
     *
     * @param fullString is String of input location to be split
     * @return array stringParts[2] with two location substrings
     */
    private String[] splitLocation(String fullString) {

        // Array to hold string parts
        String[] stringParts = new String[2];

        // If the "fullString" contains "of " split it after "of " and put parts into "stringParts"
        if (fullString.contains("of")) {
            stringParts = fullString.split("of ");
            stringParts[0] = stringParts[0] + "of ";
        } else {
            // Else put "Near the " into first substring and set second substring equal to "fullString"
            stringParts[0] = "Near the ";
            stringParts[1] = fullString;
        }
        return stringParts;
    }

}