package com.github.app.Model;

/**
 * Created by iyadz_000 on 11/18/2018.
 */

public class GithubRepo {

    private String repoName;
    private String repoDescription;
    private String userName;
    private String starsNumber;
    private String userAvatar;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(String starsNumber) {
        this.starsNumber = starsNumber;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
