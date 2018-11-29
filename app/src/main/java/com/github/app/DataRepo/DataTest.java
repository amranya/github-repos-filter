package com.github.app.DataRepo;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.github.app.Adapter.ReposAdapter;
import com.github.app.Model.GithubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyadz_000 on 11/26/2018.
 */

public class DataTest {

    private static List<GithubRepo> repos = new ArrayList<>();
    private static int page = 1;

    public static void getDataTest(final Context ctx, final ReposAdapter adapter) {

        adapter.updateList(repos);
        repos.add(null);
        adapter.notifyItemInserted(repos.size() - 1);

        Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {

                Toast.makeText(ctx, "page number: " + String.valueOf(page), Toast.LENGTH_SHORT).show();

                repos.remove(repos.size() - 1);
                adapter.notifyItemRemoved(repos.size());

                //populating adapter test

                for (int i = 0; i < 10; i++) {

                    GithubRepo repo = new GithubRepo();
                    repo.setRepoName("repo" + i + "page" + page);
                    repo.setRepoDescription("repo desc" + i);
                    repo.setStarsNumber("" + i);
                    repo.setUserName("user" + i);
                    repo.setUserAvatar("http://i.imgur.com/DvpvklR.png");

                    repos.add(repo);

                    adapter.notifyItemInserted(repos.size() - 1);

                }

                page++;
            }
        };
        handler.postDelayed(runnable, 5000);


    }

}
