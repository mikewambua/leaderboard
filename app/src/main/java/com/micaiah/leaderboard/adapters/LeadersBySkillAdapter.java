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
import com.micaiah.leaderboard.modal.LeadersListBySkills;
import com.micaiah.leaderboard.R;

import java.util.List;

public class LeadersBySkillAdapter extends RecyclerView.Adapter<LeadersBySkillAdapter.ViewHolder> {

    private Context mContext;
    private List<LeadersListBySkills> mLeaderList;
    RequestOptions mOptions;

    public LeadersBySkillAdapter(Context context, List<LeadersListBySkills> leaderList) {
        mContext = context;
        mLeaderList = leaderList;

        mOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.placeholder);
    }

    @NonNull
    @Override
    public LeadersBySkillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.leader_skills_row, parent, false);

        return new LeadersBySkillAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersBySkillAdapter.ViewHolder holder, int position) {
        holder.mTextViewName.setText(mLeaderList.get(position).getName());
        holder.mTextViewCountry.setText(mLeaderList.get(position).getCountry());
        holder.mTextViewHours.setText(mLeaderList.get(position).getScore());

        //Load image with Glide
        Glide.with(mContext).load(mLeaderList.get(position))
                .apply(mOptions)
                .into(holder.mLeaderBadge);

    }

    @Override
    public int getItemCount() {
        return mLeaderList.size();
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
