package com.github.app.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.github.app.Adapter.ReposAdapter;
import com.github.app.Model.GithubRepo;
import com.github.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ReposAdapter adapter;
    List<GithubRepo> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        manager = new LinearLayoutManager(this);

        //populating adapter test

        for(int i=0;i<10;i++){

            GithubRepo repo = new GithubRepo();
            repo.setRepoName("repo" + i);
            repo.setRepoDescription("repo desc" + i);
            repo.setStarsNumber(""+ i);
            repo.setUserName("user" + i);
            repo.setUserAvatar("http://i.imgur.com/DvpvklR.png");

            repos.add(repo);
        }

        adapter = new ReposAdapter(this, repos);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }
}
