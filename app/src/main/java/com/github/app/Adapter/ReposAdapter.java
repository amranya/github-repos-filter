package com.github.app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.app.Model.GithubRepo;
import com.github.app.R;
import com.squareup.picasso.Picasso;

/**
 * Created by iyadz_000 on 11/26/2018.
 */
import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private Context context;
    private List<GithubRepo> repos;

    public ReposAdapter(Context context, List<GithubRepo> repos) {
        this.context = context;
        this.repos = repos;
    }

    @Override
    public int getItemViewType(int position) {

        return repos.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == VIEW_TYPE_ITEM) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.github_view, parent, false);
            return new RepoViewHolder(view);

        } else if (viewType == VIEW_TYPE_LOADING) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.progress_bar, parent, false);
            return new LoadingHolder(view);

        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof RepoViewHolder) {

            RepoViewHolder repoViewHolder = (RepoViewHolder) holder;

            GithubRepo repo = repos.get(position);
            repoViewHolder.RepoName.setText(repo.getRepoName());
            repoViewHolder.RepoDescription.setText(repo.getRepoDescription());
            repoViewHolder.RepoStars.setText(repo.getStarsNumber());
            repoViewHolder.UserName.setText(repo.getUserName());
            Picasso.get()
                    .load(repo.getUserAvatar())
                    .resize(60, 60)
                    .placeholder(R.drawable.avatar)
                    .into(repoViewHolder.UserAvatar);

        } else if (holder instanceof LoadingHolder) {

            LoadingHolder loadingHolder = (LoadingHolder) holder;

            loadingHolder.progressBar.setIndeterminate(true);


        }


    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void updateList(List<GithubRepo> list) {

        repos = list;

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

    public class LoadingHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

        }
    }

}
