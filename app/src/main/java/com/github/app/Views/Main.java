package com.github.app.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import com.github.app.Adapter.ReposAdapter;
import com.github.app.DataRepo.DataTest;
import com.github.app.Model.GithubRepo;
import com.github.app.R;


import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ReposAdapter adapter;
    List<GithubRepo> repos = new ArrayList<>();

    boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        page = 0;

        recyclerView = findViewById(R.id.my_recycler_view);
        manager = new LinearLayoutManager(this);

        getData();

        adapter = new ReposAdapter(this, repos);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    getData();
                    adapter.notifyItemInserted(repos.size() - 1);
                }
            }
        });


    }


    public void getData(){

        repos = DataTest.getDataTest(page);
        page++;

    }




}
