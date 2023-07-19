/**
 * Author: Ziang Sun (ziangs)
 * Last Modified: April 8, 2023
 *
 * GetStats performs the backgroundTask of sending the request to the web service
 * (runs on the background thread to avoid stuck).
 * After finished, it calls NBATool's responseReady method, which displays the
 * collected data from the web service (web service does all the business logic and work),
 * the Android app just makes use of the JSON.
 *
 */
package ds.edu.distributedapplication;

import android.app.Activity;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
//import org.bson.Document;


import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import androidx.annotation.RequiresApi;


public class GetStats {
    NBATool nbat = null;   // for callback
    String firstName = null;       // search FirstName
    String lastName = null;        // search LastName
    String season = null;          // search season
    String JSON = null;            // returned JSON

    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param season
     * @param activity
     * @param nbat
     */
    public void search(String firstName, String lastName, String season, Activity activity, NBATool nbat) {
        this.nbat = nbat;
        this.firstName = firstName;
        this.lastName = lastName;
        this.season = season;
        new BackgroundTask(activity).execute();
    }

    private class BackgroundTask {

        private Activity activity; // The UI thread

        public BackgroundTask(Activity activity) {
            this.activity = activity;
        }

        private void startBackground() {
            new Thread(new Runnable() {
                public void run() {
                    doInBackground();
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            onPostExecute();
                        }
                    });
                }
            }).start();
        }

        private void execute(){
            startBackground();
        }

        private void doInBackground() {
            JSON = search(firstName, lastName, season);
        }

        public void onPostExecute() {
            nbat.responseReady(JSON);
        }


        /**
         * Make an HTTPS request to the web service on Github and get the response of the
         * needed JSON.
         * With reference to https://github.com/CMU-Heinz-95702/lab7-rest-programming
         * @param firstName
         * @param lastName
         * @param season
         * @return
         */
        private String search(String firstName, String lastName, String season) {

            //https://ziangit-literate-space-orbit-x5w4r6x55v93pj9r-8080.preview.app.github.dev/

            HttpsURLConnection conn;
            int status = 0;
            Result result = new Result();
            try {
                // this is the URL needed to connect to the web service on GitHub
                // With parameters of first name, last name, season, along with
                // dashboard=0 to avoid the dashboard page. The dashboard is the HTML page, but
                // the Android app just needs the JSON from the web service.

                // change github codespace


                // https://ziangit-ubiquitous-space-fishstick-9r5x6r4v9g9cxqvr-8080.preview.app.github.dev/
//                URL url = new URL("https://ziangit-probable-journey-v9r5p974p9x2xqrp-8080." +
//                        "preview.app.github.dev/getNBAStats?"
//                        + "dashboard=0&" + "firstName="+ firstName + "&lastName=" +
//                        lastName + "&season=" + season);

                URL url = new URL("https://ziangit-ubiquitous-space-fishstick-9r5x6r4v9g9cxqvr-8080." +
                        "preview.app.github.dev/getNBAStats?"
                        + "dashboard=0&" + "firstName="+ firstName + "&lastName=" +
                        lastName + "&season=" + season);


                conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                conn.setRequestProperty("Accept", "text/plain");

                status = conn.getResponseCode();

                result.setResponseCode(status);
                result.setResponseText(conn.getResponseMessage());

                // Check the status code to see what is going on on the server
                if (status == 200) {
                    String responseBody = getResponseBody(conn);
                    result.setResponseText(responseBody);
                }
                else{
                    System.out.println("Status code not 200");
                }

                conn.disconnect();

            }
            // handle exceptions
            catch (MalformedURLException e) {
                System.out.println("URL Exception thrown" + e);
            } catch (IOException e) {
                System.out.println("IO Exception thrown" + e);
            } catch (Exception e) {
                System.out.println("IO Exception thrown" + e);
            }
            return result.getResponseText();



        }

        /**
         * Get the response body
         * Cited from:
         * https://github.com/CMU-Heinz-95702/lab7-rest-programming
         * @param conn
         * @return
         * response text
         */

        public String getResponseBody(HttpsURLConnection conn) {
            String responseText = "";
            try {
                String output = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                while ((output = br.readLine()) != null) {
                    responseText += output;
                }
                conn.disconnect();
            } catch (IOException e) {
                System.out.println("Exception caught " + e);
            }
            return responseText;
        }


    }
}

