package com.example.max.githubuserslist.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.max.githubuserslist.R;
import com.example.max.githubuserslist.adapters.GitHubAdapter;
import com.example.max.githubuserslist.helpers.EndlessRecyclerViewScrollListener;
import com.example.max.githubuserslist.request_methods.GitHubRequests;
import com.example.max.githubuserslist.response_models.GithubUser;
import com.example.max.githubuserslist.utils.MessageEvent;
import com.example.max.githubuserslist.utils.Messages;
import com.example.max.githubuserslist.utils.Settings;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Max on 01.01.2018.
 */

public class GitHubUsersListFragments extends Fragment {

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.github_users_list)
    RecyclerView gitHubUsersRecycle;

    @BindView(R.id.load_bar)
    ProgressBar loadBar;

    Parcelable layoutManagerSavedState;

    ArrayList<GithubUser> gitHubUsersList;

    boolean savedState;

    int bufferPage;

    int since;

    private GitHubAdapter gitHubAdapter;

    private LinearLayoutManager layoutManager;

    private EndlessRecyclerViewScrollListener scrollListener;

    public static GitHubUsersListFragments newInstance() {
        return new GitHubUsersListFragments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_github_users_list, container, false);
        ButterKnife.bind(this, v);

        gitHubAdapter = new GitHubAdapter();
        gitHubUsersRecycle.setAdapter(gitHubAdapter);

        layoutManager = new LinearLayoutManager(getContext());

        gitHubUsersRecycle.setLayoutManager(layoutManager);

        if(savedState){
            gitHubAdapter.restoreData(gitHubUsersList);
            // listNewsPostSmall.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
            restoreLayoutManagerPosition();

        }

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //if(vidMeVideoAdapter.getItemCount()<totalCount && totalCount!=0)
                    loadNextDataFromApi(since);
                    bufferPage = page;
            }
        };
       // scrollListener.setVisibleThreshold(2);
        gitHubUsersRecycle.addOnScrollListener(scrollListener);
        if(savedState)
            scrollListener.setCurrentPage(bufferPage);
        gitHubUsersRecycle.getRecycledViewPool().setMaxRecycledViews(0, 0);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFirstUsers();
                gitHubAdapter.clearData();
                gitHubAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                since = 0;
                if(gitHubUsersList!=null)
                    gitHubUsersList.clear();
            }
        });
        return v;
    }

    private void loadNextDataFromApi(int since) {
        requestNextUsers(since);
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        switch(event.message) {
            case Messages.RESPONSE_GITHUB_USERS:
                hideProgressBar();
                ArrayList<GithubUser> githubUsers = (ArrayList<GithubUser>) event.link;
                since = githubUsers.get(Settings.PER_PAGE-1).getId();
                gitHubAdapter.setData(githubUsers);
                gitHubAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!savedState)
            requestFirstUsers();
        savedState = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        gitHubUsersList = gitHubAdapter.getData();
        layoutManagerSavedState = gitHubUsersRecycle.getLayoutManager().onSaveInstanceState();
        savedState = true;
    }

    public void showProgressBar() {
        loadBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        loadBar.setVisibility(View.GONE);
    }

    private void requestFirstUsers() {
        showProgressBar();
        GitHubRequests.requestUsers(0);
    }

    private void requestNextUsers(int since){
        showProgressBar();
        GitHubRequests.requestUsers(since);
    }

    private void restoreLayoutManagerPosition() {
        if (layoutManagerSavedState != null) {
            gitHubUsersRecycle.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
        }
    }


}
