package example.com.radpong.PlayerActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import example.com.radpong.Game;
import example.com.radpong.Player;
import example.com.radpong.R;
import example.com.radpong.callbacks.APICallback;
import example.com.radpong.callbacks.GetPlayersGamesCallback;
import example.com.radpong.radpong.GetGamesForPlayerTask;

/**
 * Created by User on 18/02/2017.
 */

public class PlayerGameFragment extends Fragment implements GetPlayersGamesCallback{

    Player player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_layout, container, false);

        if (player != null) {
            GetGamesForPlayerTask gamesForPlayerTask = new GetGamesForPlayerTask(this);
            gamesForPlayerTask.execute(player.getId());
        }
        return view;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void foundGames(ArrayList<Game> games) {
        games.toString();
    }
}
