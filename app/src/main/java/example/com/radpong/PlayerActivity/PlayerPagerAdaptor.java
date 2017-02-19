package example.com.radpong.PlayerActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import example.com.radpong.Player;

/**
 * Created by User on 18/02/2017.
 */

public class PlayerPagerAdaptor extends FragmentStatePagerAdapter {
    public static final int PLAYER_PAGE = 0;
    public static final int PLAYER_GAME_PAGE = 1;

    PlayerFragment playerFragment = null;
    PlayerGameFragment playerGameFragment = null;

    Player player;


    public PlayerPagerAdaptor(FragmentManager fm, Player player) {
        super(fm);
        this.player = player;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == PLAYER_PAGE) {
            if (playerFragment == null) {
                playerFragment = new PlayerFragment();
                playerFragment.setPlayer(player);
            }
            return playerFragment;

        } else if (position == PLAYER_GAME_PAGE) {
            if (playerGameFragment == null) {
                playerGameFragment = new PlayerGameFragment();
                playerGameFragment.setPlayer(player);
            }
            return playerGameFragment;
        }
        return new PlayerFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
