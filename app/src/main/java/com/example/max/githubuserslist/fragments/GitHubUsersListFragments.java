package com.example.max.githubuserslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
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
    RecyclerView githubUsersList;

    @BindView(R.id.load_bar)
    ProgressBar loadBar;

    private GitHubAdapter gitHubAdapter;

    private LinearLayoutManager layoutManager;

    private EndlessRecyclerViewScrollListener scrollListener;

    public static GitHubUsersListFragments newInstance() {
        return new GitHubUsersListFragments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_github_users_list, container, false);
        ButterKnife.bind(this, v);

        gitHubAdapter = new GitHubAdapter(null);
        githubUsersList.setAdapter(gitHubAdapter);

        layoutManager = new LinearLayoutManager(getContext());

        githubUsersList.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //if(vidMeVideoAdapter.getItemCount()<totalCount && totalCount!=0)
                    loadNextDataFromApi(page);
            }
        };
       // scrollListener.setVisibleThreshold(2);
        githubUsersList.addOnScrollListener(scrollListener);
        githubUsersList.getRecycledViewPool().setMaxRecycledViews(0, 0);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFirstUsers();
                gitHubAdapter.clearData();
                gitHubAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }

    private void loadNextDataFromApi(int page) {
        requestNextUsers(page);
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        switch(event.message) {
            case Messages.RESPONSE_GITHUB_USERS:
                hideProgressBar();

               ArrayList<GithubUser> githubUsers = (ArrayList<GithubUser>) event.link;


                    gitHubAdapter.setData(githubUsers);
                    gitHubAdapter.notifyDataSetChanged();
//                else{
//                    vidMeVideoAdapter.addData(videosFromVidMe.getVideoVidMeArrayList());
//                    vidMeVideoAdapter.notifyItemRangeChanged(vidMeVideoAdapter.getItemCount()-Settings.VIDEOS_LIMIT, Settings.VIDEOS_LIMIT);
//                }


                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        requestFirstUsers();
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

    private void requestNextUsers(int offset){
        showProgressBar();
        GitHubRequests.requestUsers(offset);
    }


}
