package com.micaiah.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.micaiah.leaderboard.modal.LeaderListByHours;
import com.micaiah.leaderboard.R;

import java.util.List;

public class LeadersByHourAdapter extends RecyclerView.Adapter<LeadersByHourAdapter.ViewHolder>{
    private Context mContext;
    private List<LeaderListByHours> mLeaderListHour;
    RequestOptions mOptions;

    public LeadersByHourAdapter(Context context, List<LeaderListByHours> leaderList) {
        mContext = context;
        mLeaderListHour = leaderList;

        mOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.placeholder);
    }

    @NonNull
    @Override
    public LeadersByHourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.leaderboard_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersByHourAdapter.ViewHolder holder, int position) {
        holder.mTextViewName.setText(mLeaderListHour.get(position).getName());
        holder.mTextViewCountry.setText(mLeaderListHour.get(position).getCountry());
        holder.mTextViewHours.setText(mLeaderListHour.get(position).getHours());

        //        Load image with Glide library
        Glide.with(mContext)
                .load(mLeaderListHour.get(position).getBadgeUrl())
                .apply(mOptions)
                .into(holder.mLeaderBadge);

    }

    @Override
    public int getItemCount() {
        return mLeaderListHour.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewName;
        TextView mTextViewCountry;
        TextView mTextViewHours;
        ImageView mLeaderBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.tvName);
            mTextViewCountry = itemView.findViewById(R.id.tvCountry);
            mTextViewHours = itemView.findViewById(R.id.tvHours);
            mLeaderBadge = itemView.findViewById(R.id.image_badge);
        }
    }
}
