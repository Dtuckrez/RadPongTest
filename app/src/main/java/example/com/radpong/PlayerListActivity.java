package example.com.radpong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import example.com.radpong.customUI.PlayerListViewAdaptor;
import example.com.radpong.radpong.CreatePlayerTask;
import example.com.radpong.radpong.GetAllPlayerTask;
import example.com.radpong.callbacks.APICallback;

public class PlayerListActivity extends AppCompatActivity implements APICallback {

    RecyclerView playerListView;
    RecyclerView.LayoutManager mLayoutManager;
    PlayerListViewAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                showNewUserDialog();
            }
        });

        playerListView = (RecyclerView) findViewById(R.id.player_list_view);
        // Fixed size as the view dimensions should not change.
        playerListView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        playerListView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        GetAllPlayerTask getAllPlayerTask = new GetAllPlayerTask(this);
        getAllPlayerTask.execute();
    }

    public void showNewUserDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Player");
        final EditText input = new EditText(this);
        input.setHint("Enter Players Name.");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createPlayer(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void createPlayer(String playerName) {
        CreatePlayerTask createPlayerTask = new CreatePlayerTask(this);
        createPlayerTask.execute(playerName);
    }

    @Override
    public void foundAllPlayer(ArrayList<Player> players) {
        if (players != null) {
            // specify an adapter (see also next example)
            adapter = new PlayerListViewAdaptor(this, players);
            playerListView.setAdapter(adapter);
        }
    }

    @Override
    public void addedPlayerSuccess(Player player) {
        adapter.mDataset.add(player);
        adapter.notifyDataSetChanged();
    }
}
