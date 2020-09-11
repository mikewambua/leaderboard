package com.micaiah.leaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.micaiah.leaderboard.R;
import com.micaiah.leaderboard.adapters.LeadersByHourAdapter;
import com.micaiah.leaderboard.modal.LeaderListByHours;

import java.util.List;

public class FragmentByLearningHours extends Fragment {
    RecyclerView mRecyclerView;
    private List<LeaderListByHours> mLeaderList;

    public FragmentByLearningHours(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_by_learning_hours, container,false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<LeaderListByHours> mLearners = mLeaderList;
        LeadersByHourAdapter mAdapter = new LeadersByHourAdapter(getContext(),mLearners);
        mRecyclerView = view.findViewById(R.id.recyclerView_fragment_hours);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
