package example.com.radpong.callbacks;

import java.util.ArrayList;

import example.com.radpong.Player;

/**
 * Created by User on 15/02/2017.
 */

public interface APICallback {
    void foundAllPlayer(ArrayList<Player> players);
    void addedPlayerSuccess(Player player);
}
