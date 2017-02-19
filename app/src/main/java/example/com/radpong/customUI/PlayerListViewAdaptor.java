package example.com.radpong.customUI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.radpong.Player;
import example.com.radpong.PlayerActivity.PlayerActivity;
import example.com.radpong.R;

/**
 * Created by User on 15/02/2017.
 */

public class PlayerListViewAdaptor extends RecyclerView.Adapter<PlayerListViewAdaptor.ViewHolder> {

    public ArrayList<Player> mDataset;
    public Context context;


    public PlayerListViewAdaptor(Context context, ArrayList<Player> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    @Override
    public PlayerListViewAdaptor.ViewHolder onCreateViewHolder(final ViewGroup parent,
                                                               int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("PLAYER", mDataset.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
