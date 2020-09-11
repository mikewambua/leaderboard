package com.micaiah.leaderboard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.micaiah.leaderboard.adapters.LeadersBySkillAdapter;
import com.micaiah.leaderboard.adapters.ViewPagerAdapter;
import com.micaiah.leaderboard.fragments.FragmentByIQSkills;
import com.micaiah.leaderboard.fragments.FragmentByLearningHours;
import com.micaiah.leaderboard.modal.LeaderListByHours;
import com.micaiah.leaderboard.adapters.LeadersByHourAdapter;
import com.micaiah.leaderboard.R;
import com.micaiah.leaderboard.modal.LeadersListBySkills;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {
    private List<LeaderListByHours> mLeaderBoardList;
    private List<LeadersListBySkills> mLeadersIQSkills;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TabLayout mTabLayout = findViewById(R.id.tab_layout);
        final ViewPager mViewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adding adapter to the ViewPager
        mViewPagerAdapter.addFragment(new FragmentByLearningHours(), "Learning Hours");
        mViewPagerAdapter.addFragment(new FragmentByIQSkills(),"Leaders IQ Skills");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mLeaderBoardList = new ArrayList<>();
        mLeadersIQSkills = new ArrayList<>();

//        Setting tab click listener for the TabLayout
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 1){
                    getLeadersData();
                }
                else{
                    getLeaderSkillsData();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void getLeaderSkillsData() {
//        mRecyclerView = findViewById(R.id.recyclerView);
        final ProgressBar mProgressBar = findViewById(R.id.pb_loading);
        mProgressBar.setVisibility(View.VISIBLE);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://gadsapi.herokuapp.com/api/skilliq";

// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        JSONObject object = null;
                        for(int i = 0; i <response.length(); i++){
                            try {
                                object = response.getJSONObject(i);
                                LeadersListBySkills mListIQSkill = new LeadersListBySkills();
                                mListIQSkill.setName(object.getString("name"));
                                mListIQSkill.setScore(object.getInt("score"));
                                mListIQSkill.setCountry(object.getString("country"));
                                mListIQSkill.setBadgeUrl(object.getString("badgeUrl"));
                                mLeadersIQSkills.add(mListIQSkill);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        getLearnersDataBySkill(mLeadersIQSkills);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);

    }

    private void getLearnersDataBySkill(List<LeadersListBySkills> leadersIQSkills) {
        LeadersBySkillAdapter mAdapter = new LeadersBySkillAdapter(this,leadersIQSkills);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getLeadersData() {
//        mRecyclerView = findViewById(R.id.recyclerView);
        final ProgressBar mProgressBar = findViewById(R.id.pb_loading);
        mProgressBar.setVisibility(View.VISIBLE);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://gadsapi.herokuapp.com/api/hours";

// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                         mProgressBar.setVisibility(View.INVISIBLE);
                        JSONObject object = null;
                        for(int i = 0; i <response.length(); i++){
                            try {
                                object = response.getJSONObject(i);
                                LeaderListByHours mList = new LeaderListByHours();
                                mList.setName(object.getString("name"));
                                mList.setHours(object.getInt("hours"));
                                mList.setCountry(object.getString("country"));
                                mList.setBadgeUrl(object.getString("badgeUrl"));
                                mLeaderBoardList.add(mList);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        displayDataFromRecyclerView(mLeaderBoardList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }

    private void displayDataFromRecyclerView(List<LeaderListByHours> leaderBoardList) {
        LeadersByHourAdapter adapter = new LeadersByHourAdapter(this,mLeaderBoardList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit_button:
                Intent intent = new Intent(this,ProjectSubmissionActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}