package com.example.trivial.modal;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Score {

    private String username;
    private int score;

    public Score() {

    }

    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return username.equals(score.username);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
