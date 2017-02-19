package example.com.radpong;

import java.io.Serializable;

/**
 * Created by User on 18/02/2017.
 */

public class Game implements Serializable {

    String id;
    createdBy createdBy;
    String state;
    double createdAt;
    double startDate;
    double endDate;
    // playerOne Map
    // playerTwo Map
    boolean isCurrent;

    public Game() {}

    public Game(String id, String state, double startDate, double endDate, boolean isCurrent) {
        this.id = id;
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getStartDate() {
        return startDate;
    }

    public void setStartDate(double startDate) {
        this.startDate = startDate;
    }

    public double getEndDate() {
        return endDate;
    }

    public void setEndDate(double endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(double createdAt) {
        this.createdAt = createdAt;
    }

    public class createdBy implements Serializable {
        String name;
        boolean isPubic;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPubic() {
            return isPubic;
        }

        public void setPubic(boolean pubic) {
            isPubic = pubic;
        }
    }

    public class GamePlayer implements Serializable {
        Player player;
        int score;
        String state;
        Stats stats;

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Stats getStats() {
            return stats;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }
    }

    public class Stats implements Serializable {
        int forcedErrors;
        int unforcedErrors;
        int wins;

        public int getForcedErrors() {
            return forcedErrors;
        }

        public void setForcedErrors(int forcedErrors) {
            this.forcedErrors = forcedErrors;
        }

        public int getUnforcedErrors() {
            return unforcedErrors;
        }

        public void setUnforcedErrors(int unforcedErrors) {
            this.unforcedErrors = unforcedErrors;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }
    }

    public class summary implements Serializable {
        boolean draw;
        String loser;
        String winner;

        public boolean isDraw() {
            return draw;
        }

        public void setDraw(boolean draw) {
            this.draw = draw;
        }

        public String getLoser() {
            return loser;
        }

        public void setLoser(String loser) {
            this.loser = loser;
        }

        public String getWinner() {
            return winner;
        }

        public void setWinner(String winner) {
            this.winner = winner;
        }
    }
}
