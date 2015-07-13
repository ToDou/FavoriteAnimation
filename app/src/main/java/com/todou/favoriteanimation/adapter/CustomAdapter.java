package com.todou.favoriteanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.todou.favoriteanimation.R;
import com.todou.favoriteanimation.ui.activity.MainActivity;
import com.todou.favoriteanimation.utils.ViewUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by loopeer on 2015/7/13.
 */
public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<String> mData;

    public CustomAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mData = new ArrayList<>();
    }

    public void updateData(ArrayList<String> data) {
        setData(data);
        notifyDataSetChanged();
    }

    private void setData(ArrayList<String> data) {
        this.mData.clear();
        this.mData.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mLayoutInflater.inflate(R.layout.list_item_main, parent, false);
        return new MainItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainItemViewHolder mainHolder = (MainItemViewHolder) holder;
        mainHolder.bind(mData.get(position));
        mainHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = ViewUtils.getNumLocation(v);
                ((MainActivity) mContext).doAnimation(location[0], location[1]);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MainItemViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.text)
        TextView mText;
        @InjectView(R.id.button)
        Button mButton;

        public MainItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bind(String string) {
            mText.setText(string);
        }
    }
}
