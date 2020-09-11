package com.micaiah.leaderboard.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.micaiah.leaderboard.R;

public class MainActivity extends AppCompatActivity {
    private ImageView launchImage;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchImage = findViewById(R.id.launch_img);
        launchImage.setImageResource(R.mipmap.gads);
    }

    public void navigateToLeaderBoard(View view) {

        Intent intent = new Intent(this, LeaderBoardActivity.class);
        startActivity(intent);

    }

}