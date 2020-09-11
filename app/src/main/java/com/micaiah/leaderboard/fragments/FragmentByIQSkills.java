package com.micaiah.leaderboard.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.micaiah.leaderboard.R;
import com.micaiah.leaderboard.adapters.LeadersBySkillAdapter;
import com.micaiah.leaderboard.modal.LeadersListBySkills;

import java.util.List;

public class FragmentByIQSkills extends Fragment {
    RecyclerView mRecyclerView;
    List<LeadersListBySkills> mLearnerSkill;



    public FragmentByIQSkills() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_by_skills, container,false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<LeadersListBySkills> mListSkills = mLearnerSkill;
        LeadersBySkillAdapter mAdapter = new LeadersBySkillAdapter(getContext(),mListSkills);
        mRecyclerView = view.findViewById(R.id.recyclerView_fragment_skills);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
