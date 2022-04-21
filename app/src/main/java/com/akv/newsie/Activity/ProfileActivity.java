package com.akv.newsie.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.akv.newsie.R;
import com.akv.newsie.Util.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private TextView fullName;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);
        initView();
    }

    public void initView() {
        fullName = findViewById(R.id.tv_fullname);
        email = findViewById(R.id.tv_email);

        fullName.setText(sessionManager.getUsername());
        email.setText(sessionManager.getEmail());

    }
}