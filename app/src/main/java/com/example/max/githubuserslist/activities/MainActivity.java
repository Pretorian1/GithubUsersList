package com.example.max.githubuserslist.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.max.githubuserslist.R;
import com.example.max.githubuserslist.fragments.GitHubUsersListFragments;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return GitHubUsersListFragments.newInstance();
    }

}
