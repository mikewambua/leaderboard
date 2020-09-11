package com.micaiah.leaderboard.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.micaiah.leaderboard.R;
import com.micaiah.leaderboard.apis.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmissionActivity extends AppCompatActivity {
    EditText mEmailAddress, mFirstName, mLastName, mLinkToProject;
    Button mSubmit;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        Toolbar toolbar = findViewById(R.id.toolbar_custom);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initialize EditTexts
        mFirstName = findViewById(R.id.text_first_name);
        mLastName = findViewById(R.id.text_last_name);
        mEmailAddress = findViewById(R.id.text_email);
        mLinkToProject = findViewById(R.id.github_link);
        mSubmit = findViewById(R.id.button_submit);
//        mProgressBar = findViewById(R.id.progress_bar);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog =
                        new AlertDialog.Builder(ProjectSubmissionActivity.this).create();
                View mCustomView = getLayoutInflater().inflate(R.layout.positive_custom_alert_dialog, null);

                alertDialog.setView(mCustomView);
                mCustomView.findViewById(R.id.image_close_popup).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                mCustomView.findViewById(R.id.button_accept).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitData();
                        mFirstName.setText("");
                        mLastName.setText("");
                        mEmailAddress.setText("");
                        mLinkToProject.setText("");
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void submitData() {
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String emailAddress = mEmailAddress.getText().toString().trim();
        String linkToProject = mLinkToProject.getText().toString().trim();

        //Validate EditText fields
        if (firstName.isEmpty()) {
            mFirstName.setError("Please enter your First Name");
            mFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            mLastName.setError("Please enter your Last Name");
            mLastName.requestFocus();
            return;
        }
        if (emailAddress.isEmpty()) {
            mEmailAddress.setError("Email is required");
            mEmailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            mEmailAddress.setError("Enter a valid email");
            mEmailAddress.requestFocus();
            return;
        }

        if (linkToProject.isEmpty()) {
            mLinkToProject.setError("Github link is required");
            mLinkToProject.requestFocus();
            return;
        }

        if (!Patterns.WEB_URL.matcher(linkToProject).matches()) {
            mLinkToProject.setError("Enter a valid link");
            mLinkToProject.requestFocus();
            return;
        }
        //call to retrofit ApiManager
        retrofit2.Call<ResponseBody> call = RetrofitClient.getInstance()
                .getApi()
                .formResponse(firstName, lastName, emailAddress, linkToProject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                  Todo call the positive popup alert dialog here
                if(response.isSuccessful()) {
                    View mLayout =
                            getLayoutInflater().inflate(R.layout.submission_successful_alert_dialog, null);
                    final Toast mToast = new Toast(getApplicationContext());
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setView(mLayout);
                    mToast.show();
                }else{
                    View layout =
                            getLayoutInflater().inflate(R.layout.negative_custom_alert_dialog,null);
                    final Toast mToast = new Toast(getApplicationContext());
                    mToast.setGravity(Gravity.CENTER,0,0);
                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setView(layout);
                    mToast.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//    Todo call the negative popup alert dialog here
                t.getLocalizedMessage();
            }
        });


    }
}