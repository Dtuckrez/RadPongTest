package example.com.radpong.callbacks;

import java.util.ArrayList;

import example.com.radpong.Game;

/**
 * Created by User on 18/02/2017.
 */

public interface GetPlayersGamesCallback {

    void foundGames(ArrayList<Game> games);
}
