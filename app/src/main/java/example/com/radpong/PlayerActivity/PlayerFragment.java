package example.com.radpong.PlayerActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import example.com.radpong.Player;
import example.com.radpong.R;
import example.com.radpong.radpong.GetGamesForPlayerTask;
import example.com.radpong.radpong.RenamePlayerTask;

/**
 * Created by User on 18/02/2017.
 */

public class PlayerFragment extends Fragment {
    EditText playerNameTextView;

    Player player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.player_layout, container, false);

        if (player != null) {
            playerNameTextView = (EditText) view.findViewById(R.id.player_name_txt);
            playerNameTextView.setText(player.getName());
            playerNameTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        // do your stuff here
                        RenamePlayerTask renamePlayerTask = new RenamePlayerTask();
                        renamePlayerTask.execute(player.getId(), player.getName());

//                        GetGamesForPlayerTask gamesForPlayerTask = new GetGamesForPlayerTask(null);
//                        gamesForPlayerTask.execute(player.getId());
                    }
                    return false;
                }
            });

            TextView gamesPlayed = (TextView) view.findViewById(R.id.games_played_txt);
            gamesPlayed.setText(String.valueOf(player.getStats().getGames()));
            TextView gamesWon = (TextView) view.findViewById(R.id.games_won_txt);
            gamesWon.setText(String.valueOf(player.getStats().getWins()));
            TextView gamesDrawn = (TextView) view.findViewById(R.id.games_drawn_txt);
            gamesDrawn.setText(String.valueOf(player.getStats().getDraws()));
            TextView gamesLost = (TextView) view.findViewById(R.id.games_lost_txt);
            gamesLost.setText(String.valueOf(player.getStats().getLosses()));
        }

        return view;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
