package com.github.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.app.Model.GithubRepo;
import com.github.app.R;
import com.squareup.picasso.Picasso;

/**
 * Created by iyadz_000 on 11/26/2018.
 */
import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {

    private Context context;
    private List<GithubRepo> repos;

    public ReposAdapter(Context context, List<GithubRepo> repos) {
        this.context = context;
        this.repos = repos;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.github_view, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReposAdapter.RepoViewHolder holder, int position) {

        GithubRepo repo = repos.get(position);
        holder.RepoName.setText(repo.getRepoName());
        holder.RepoDescription.setText(repo.getRepoDescription());
        holder.RepoStars.setText(repo.getStarsNumber());
        holder.UserName.setText(repo.getUserName());
        Picasso.get()
                .load(repo.getUserAvatar())
                .resize(60, 60)
                .placeholder(R.drawable.avatar)
                .into(holder.UserAvatar);


    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {

        TextView RepoName, RepoDescription, RepoStars, UserName;
        ImageView UserAvatar;

        public RepoViewHolder(View itemView) {
            super(itemView);
            RepoName = (TextView) itemView.findViewById(R.id.repoNameView);
            RepoDescription = (TextView) itemView.findViewById(R.id.repoDescView);
            RepoStars = (TextView) itemView.findViewById(R.id.repoStarsView);
            UserName = (TextView) itemView.findViewById(R.id.userNameView);
            UserAvatar = (ImageView) itemView.findViewById(R.id.avatarView);

        }
    }

}
