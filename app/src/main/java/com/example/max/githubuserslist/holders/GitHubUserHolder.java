package com.example.max.githubuserslist.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.max.githubuserslist.R;
import com.example.max.githubuserslist.response_models.GithubUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Max on 31.12.2017.
 */

public class GitHubUserHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.github_user_avatar)
    CircleImageView userAvatar;

    @BindView(R.id.login_textview)
    TextView loginTextView;

    @BindView(R.id.html_link_textview)
    TextView htmlLink;

    View itemView;

    public GitHubUserHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public void bindGitHubHolder(GithubUser githubUser){

       if(githubUser.getAvatarUrl()!=null)
           Glide.with(itemView).load(githubUser.getAvatarUrl()).into(userAvatar);

       if(githubUser.getLogin()!=null)
           loginTextView.setText(githubUser.getLogin());

       if(githubUser.getHtmlUrl()!=null)
           htmlLink.setText(githubUser.getHtmlUrl());
    }
}
