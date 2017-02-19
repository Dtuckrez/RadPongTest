package example.com.radpong;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by User on 15/02/2017.
 */
public class JsonHelper {
    private static JsonHelper ourInstance = new JsonHelper();

    public static JsonHelper getInstance() {
        return ourInstance;
    }

    private JsonHelper() {
    }

    public ArrayList<Player> createPlayerListFromJson(JsonArray players) {
        ArrayList<Player> playersList = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            Player p = new Gson().fromJson(players.get(i), Player.class);
            if (p != null) {
                playersList.add(p);
            }
        }

        return playersList;
    }

    public ArrayList<Game> createPlayersGameListFromJson(JsonArray games) {
        ArrayList<Game> gamesList = new ArrayList<>();

        for (int i = 0; i < games.size(); i++) {
            Game g = new Gson().fromJson(games.get(i), Game.class);
            if (g != null) {
                gamesList.add(g);
            }
        }

        return gamesList;
    }

    public Player createPlayerFromJson(JsonObject player) {
        Player p = new Gson().fromJson(player.getAsJsonObject(), Player.class);

        return p;
    }
}