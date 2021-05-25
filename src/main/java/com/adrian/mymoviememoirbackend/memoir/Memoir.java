package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.Person.Person;
import com.adrian.mymoviememoirbackend.cinema.Cinema;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity( name = "Memoir")
@Table(name = "Memoir")
public class Memoir {

    @Id
    @SequenceGenerator(
            name = "memoir_sequence",
            sequenceName = "memoir_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "memoir_sequence"
    )
    @Column(
            name = "mem_id",
            updatable = false
    )
    private long id;

    @Column(
            name = "movie_name"
    )
    private String movieName;

    @Column(
            name = "release_date"
    )
    private LocalDate releaseDate;

    @Column(
            name = "watch_datetime",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime watchDateTime;

    @Column(
            length = 512
    )
    private String comment;

    @Column(
            precision = 2,
            scale = 1
    )
    private float rating;

    @ManyToOne
    @JoinColumn(name = "cinema_id", foreignKey = @ForeignKey(name = "cinema_memoir_FK"))
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "person_memoir_FK"))
    private Person person;

    public Memoir() {
    }

    public Memoir(long id, String movieName, LocalDate releaseDate, LocalDateTime watchDateTime, String comment, float rating, Cinema cinema, Person person) {
        this.id = id;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.watchDateTime = watchDateTime;
        this.comment = comment;
        this.rating = rating;
        this.cinema = cinema;
        this.person = person;
    }

    public Memoir(String movieName, LocalDate releaseDate, LocalDateTime watchDateTime, String comment, float rating, Cinema cinema, Person person) {
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.watchDateTime = watchDateTime;
        this.comment = comment;
        this.rating = rating;
        this.cinema = cinema;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDateTime getWatchDateTime() {
        return watchDateTime;
    }

    public void setWatchDateTime(LocalDateTime watchDateTime) {
        this.watchDateTime = watchDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Person getUser() {
        return person;
    }

    public void setUser(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Memoir{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", releaseDate=" + releaseDate +
                ", watchDateTime=" + watchDateTime +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", cinema=" + cinema +
                ", user=" + person +
                '}';
    }
}
