package com.example.max.githubuserslist.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.max.githubuserslist.R;

/**
 * Created by Max on 01.01.2018.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{

        protected abstract Fragment createFragment();

        @LayoutRes
        protected int getLayoutResId() {
            return R.layout.activity_main;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(getLayoutResId());

            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.main_activity_container);

            if (fragment == null) {
                fragment = createFragment();
                fm.beginTransaction()
                        .add(R.id.main_activity_container, fragment)
                        .commit();
            }
        }

}
