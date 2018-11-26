package com.github.app.DataRepo;

import com.github.app.Adapter.ReposAdapter;
import com.github.app.Model.GithubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyadz_000 on 11/26/2018.
 */

public class DataTest {

    private static List<GithubRepo> repos = new ArrayList<>();

    public static List<GithubRepo> getDataTest(int page) {

        //populating adapter test

        for(int i=0;i<10;i++){

            GithubRepo repo = new GithubRepo();
            repo.setRepoName("repo" + i + "page" + page);
            repo.setRepoDescription("repo desc" + i);
            repo.setStarsNumber(""+ i);
            repo.setUserName("user" + i);
            repo.setUserAvatar("http://i.imgur.com/DvpvklR.png");

            repos.add(repo);
        }

        return repos;

    }

}
