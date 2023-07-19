/**
 * Author: Ziang Sun (ziangs)
 * Last Modified: April 8, 2023
 *
 * Result class is used to store the responseCode and responseText from the operation
 * of HTTP connection.
 * responseCode is the HTTP status code used to check the server status.
 * responseText stores the needed data
 *
 */


package ds.edu.distributedapplication;

class Result {
    private int responseCode;
    private String responseText;

    public int getResponseCode() { return responseCode; }
    public void setResponseCode(int code) { responseCode = code; }
    public String getResponseText() { return responseText; }
    public void setResponseText(String msg) { responseText = msg; }

    public String toString() { return responseCode + ":" + responseText; }
}
