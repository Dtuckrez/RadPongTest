package example.com.radpong;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 15/02/2017.
 */

public class Player implements Serializable {

    private String id;
    private String name;
    private Stats stats;

    private ArrayList<PlayerGame> games;

    public Player() {

    }

    public Player(String id, String name, Stats stats, ArrayList<PlayerGame> games){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public ArrayList<PlayerGame> getGames() {
        return games;
    }

    public void setGames(ArrayList<PlayerGame> games) {
        this.games = games;
    }

    public class Stats implements Serializable{
        int draws = 0;
        int games = 0;
        int losses = 0;
        int shotsConceded = 0;
        int shotsDifference = 0;
        int shotsScored = 0;
        int wins = 0;

        public int getDraws() {
            return draws;
        }

        public void setDraws(int draws) {
            this.draws = draws;
        }

        public int getGames() {
            return games;
        }

        public void setGames(int games) {
            this.games = games;
        }

        public int getLosses() {
            return losses;
        }

        public void setLosses(int losses) {
            this.losses = losses;
        }

        public int getShotsConceded() {
            return shotsConceded;
        }

        public void setShotsConceded(int shotsConceded) {
            this.shotsConceded = shotsConceded;
        }

        public int getShotsDifference() {
            return shotsDifference;
        }

        public void setShotsDifference(int shotsDifference) {
            this.shotsDifference = shotsDifference;
        }

        public int getShotsScored() {
            return shotsScored;
        }

        public void setShotsScored(int shotsScored) {
            this.shotsScored = shotsScored;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }
    }

    class PlayerGame implements Serializable {
        String id = "";
        String child = "";
        boolean isCurrent = false;
        String state = "";
    }
}
