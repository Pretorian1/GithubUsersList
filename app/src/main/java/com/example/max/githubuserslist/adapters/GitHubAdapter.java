package com.example.max.githubuserslist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.max.githubuserslist.R;
import com.example.max.githubuserslist.holders.GitHubUserHolder;
import com.example.max.githubuserslist.response_models.GithubUser;

import java.util.ArrayList;

/**
 * Created by Max on 31.12.2017.
 */

public class GitHubAdapter  extends RecyclerView.Adapter<GitHubUserHolder> {

    private ArrayList<GithubUser> githubUsersArrayList;

    public GitHubAdapter(ArrayList<GithubUser> githubUsers){
        githubUsersArrayList = githubUsers;

    }

    public void setData(ArrayList<GithubUser> githubUsers){

        if(githubUsers == null){
            githubUsers = new ArrayList<>();
            githubUsers.addAll(githubUsers);
        }

        else
            githubUsers.addAll(githubUsers);
    }

    @Override
    public GitHubUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github_user, parent, false);
        return new GitHubUserHolder(v);
    }

    @Override
    public void onBindViewHolder(GitHubUserHolder holder, int position) {
        holder.bindGitHubHolder(githubUsersArrayList.get(position));

    }

    @Override
    public int getItemCount() {

        if(githubUsersArrayList==null)
            return 0;
        else
            return githubUsersArrayList.size();

    }
}
