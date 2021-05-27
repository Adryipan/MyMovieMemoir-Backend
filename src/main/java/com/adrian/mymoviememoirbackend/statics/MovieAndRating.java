package com.adrian.mymoviememoirbackend.statics;

import java.time.LocalDate;

public class MovieAndRating {

    private String movieName;
    private float rating;
    private LocalDate releaseDate;

    public MovieAndRating(String movieName, float rating, LocalDate releaseDate) {
        this.movieName = movieName;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
