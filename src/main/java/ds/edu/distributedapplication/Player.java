/**
 * Author: Ziang Sun (ziangs)
 * Last Modified: April 8, 2023
 *
 * Player class is used with Gson to break down the JSON message from the web service.
 * It has the needed data to display including TotalGames, AveragePoints, AverageReboudns,
 * and AverageAssists
 *
 */

package ds.edu.distributedapplication;

public class Player {
    private String TotalGames;
    private String AveragePoints;
    private String AverageRebounds;
    private String AverageAssists;

    public String getTotalGames() {
        return TotalGames;
    }

    public String getAveragePoints() {
        return AveragePoints;
    }

    public String getAverageRebounds() {
        return AverageRebounds;
    }

    public String getAverageAssists() {
        return AverageAssists;
    }

    public void setTotalGames(String totalGames) {
        TotalGames = totalGames;
    }

    public void setAveragePoints(String averagePoints) {
        AveragePoints = averagePoints;
    }

    public void setAverageRebounds(String averageRebounds) {
        AverageRebounds = averageRebounds;
    }

    public void setAverageAssists(String averageAssists) {
        AverageAssists = averageAssists;
    }

    @Override
    public String toString() {
        return "Player{" +
                "TotalGames='" + TotalGames + '\'' +
                ", AveragePoints='" + AveragePoints + '\'' +
                ", AverageRebounds='" + AverageRebounds + '\'' +
                ", AverageAssists='" + AverageAssists + '\'' +
                '}';
    }
}
