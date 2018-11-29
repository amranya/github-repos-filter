package com.github.app.DataRepo;


import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.app.API.GithubAPI;
import com.github.app.Adapter.ReposAdapter;
import com.github.app.Model.GithubRepo;
import com.github.app.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyadz_000 on 11/26/2018.
 */

public class GithubData {

    private static final int DAYS = 30;
    private static List<GithubRepo> repos = new ArrayList<>();
    private static RequestQueue queue;
    private static int page = 1;


    public static void getMostStarredRepos(final Context ctx, final ReposAdapter adapter) {

        adapter.updateList(repos);
        Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {

                repos.add(null);
                adapter.notifyItemInserted(repos.size() - 1);

            }
        };
        handler.post(runnable);


        queue = Volley.newRequestQueue(ctx);
        String CREATED = Utils.dateBefore(DAYS);
        String SORT = "stars";
        String ORDER = "desc";
        final String URL = GithubAPI.githubRepoURL + GithubAPI.CREATED + CREATED + GithubAPI.SORT + SORT
                + GithubAPI.ORDER + ORDER + GithubAPI.PAGE + page;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (response != null) {

                            Toast.makeText(ctx, "page number: " + String.valueOf(page), Toast.LENGTH_SHORT).show();

                            repos.remove(repos.size() - 1);
                            adapter.notifyItemRemoved(repos.size());

                            try {

                                JSONArray reposArray = response.getJSONArray("items");

                                for (int i = 0; i < reposArray.length(); i++) {

                                    JSONObject repoObject = reposArray.getJSONObject(i);
                                    JSONObject userObject = repoObject.getJSONObject("owner");

                                    GithubRepo repo = new GithubRepo();

                                    repo.setRepoName(repoObject.getString("name"));
                                    repo.setRepoDescription(repoObject.getString("description"));
                                    repo.setStarsNumber(String.valueOf(repoObject.getInt("stargazers_count")));
                                    repo.setUserName(userObject.getString("login"));
                                    repo.setUserAvatar(userObject.getString("avatar_url"));

                                    repos.add(repo);

                                    adapter.notifyItemInserted(repos.size() - 1);

                                }

                                page++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(ctx, "no more data to display", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);


    }


}


