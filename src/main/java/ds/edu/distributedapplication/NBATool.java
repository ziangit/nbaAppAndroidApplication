/**
 * Author: Ziang Sun (ziangs)
 * Last Modified: April 8, 2023
 *
 * NBATool initializes the GetStats class and sets the views.
 * It adds clickListener to the button so that when user finishes entering the data,
 * GetStats class can do the work in background and get the needed statistics from
 * the web-service deployed on GitHub (which fetches data and does business logic from
 * the third-party-API). Then, when response is ready, it shows user the processed data:
 * Total Games, Average Points, Average Assists, and Average Rebounds.
 * The application does not have access to the dashboard.
 *
 * This program uses the structure from InterestingPicture, it helps me a lot understand
 * background works.
 *
 *
 *
 */

package ds.edu.distributedapplication;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

public class NBATool extends AppCompatActivity {
    NBATool me = this;

    private boolean networkConnected = true;

    /**
     * The start of the program, sets up the view and objects.
     * When user clicks on the submit button, do the search
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final NBATool nbat = this;

        Button submitButton = (Button)findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View viewParam) {
                String firstName = ((EditText)findViewById(R.id.FirstName)).getText().toString();
                String lastName = ((EditText)findViewById(R.id.LastName)).getText().toString();
                String season = ((EditText)findViewById(R.id.Season)).getText().toString();


                System.out.println("FirstName = " + firstName);
                System.out.println("LastName = " + lastName);
                System.out.println("Season = " + season);
                GetStats gs = new GetStats();
                gs.search(firstName, lastName, season, me, nbat);
            }
        });
    }

    /**
     * When the background work is done and responseReady is called,
     * it uses the response JSON string by breaking down and show the needed
     * data to the user.
     * @param JSON
     */

    public void responseReady(String JSON) {

        //ObjectMapper objectMapper = new ObjectMapper();
        TextView FirstNameView = (EditText)findViewById(R.id.FirstName);
        TextView LastNameView = (EditText)findViewById(R.id.LastName);
        TextView SeasonView = (EditText)findViewById(R.id.Season);

        // For testing
        System.out.println("Print JSON: ");
        System.out.println(JSON);

        // Use Gson and player class to break down data
        Gson gson = new Gson();
        Player player = gson.fromJson(JSON, Player.class);
        System.out.println(player);
        String TotalGames = player.getTotalGames();
        String AveragePoints = player.getAveragePoints();
        String AverageRebounds = player.getAverageRebounds();
        String AverageAssists = player.getAverageAssists();

        TextView GameView = (TextView) findViewById(R.id.TotalGames);
        TextView PointsView = (TextView) findViewById(R.id.AveragePoints);
        TextView AssistsView = (TextView) findViewById(R.id.AverageAssists);
        TextView ReboundsView = (TextView) findViewById(R.id.AverageRebounds);
        TextView responseView = (TextView) findViewById(R.id.textView);


        // Check internet connection
        if (networkConnected == false){
            responseView.setText("Network failed! ");
            responseView.setVisibility(View.VISIBLE);
        }

        // If the player has at least one game, it means the player is valid,
        // so display the user statistics
        else if (!TotalGames.equals("0")){
            System.out.println("JSON not null, print stats");
            GameView.setText("Total Games: " + TotalGames);
            GameView.setVisibility(View.VISIBLE);
            PointsView.setText("Average Points: " + AveragePoints);
            PointsView.setVisibility(View.VISIBLE);
            AssistsView.setText("Average Assists: " + AverageAssists);
            AssistsView.setVisibility(View.VISIBLE);
            ReboundsView.setText("Average Rebounds: " + AverageRebounds);
            ReboundsView.setVisibility(View.VISIBLE);
            responseView.setText("Player statistics shown! ");
            responseView.setVisibility(View.VISIBLE);
        }

        // Else, either the player is invalid, or the player does not play a single
        // game this season, display NaN for all statistics and sets responseView
        // to "Player statistics not found! "
        else {
            GameView.setText("Total Games: NaN");
            PointsView.setText("Average Points: NaN");
            AssistsView.setText("Average Assists: NaN");
            ReboundsView.setText("Average Rebounds: NaN");
            responseView.setText("Player statistics not found! ");
            responseView.setVisibility(View.VISIBLE);
        }

        // After all, clear text for future search
        FirstNameView.setText("");
        LastNameView.setText("");
        SeasonView.setText("");

    }

}
