package example.com.radpong.PlayerActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import example.com.radpong.Player;
import example.com.radpong.R;

public class PlayerActivity extends AppCompatActivity {

    Player player;
    ViewPager viewPager;
    ToggleButton playerStatsBtn;
    ToggleButton playerGamesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        player = (Player) getIntent().getSerializableExtra("PLAYER");
        if (player == null) {
            finish();
        }

        setContentView(R.layout.player_activity_layout);
        playerGamesBtn = (ToggleButton) findViewById(R.id.player_games_btn);
        playerGamesBtn.setSelected(false);
        playerGamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playerGamesBtn.isSelected()) {
                    viewPager.setCurrentItem(1);
                    playerGamesBtn.setSelected(true);
                    playerStatsBtn.setSelected(false);
                }
            }
        });
        playerStatsBtn = (ToggleButton) findViewById(R.id.player_stats_btn);
        playerStatsBtn.setSelected(true);
        playerStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playerStatsBtn.isSelected()) {
                    viewPager.setCurrentItem(0);
                    playerStatsBtn.setSelected(true);
                    playerGamesBtn.setSelected(false);
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.player_activity_pager);
        viewPager.setAdapter(new PlayerPagerAdaptor(getSupportFragmentManager(), player));

        //
    }
}
