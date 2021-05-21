package com.adrian.mymoviememoirbackend.cinema;

import javax.persistence.*;

@Entity( name = "Cinema")
@Table(name = "Cinema")
public class Cinema {

    @Id
    @SequenceGenerator(
            name = "cinema_sequence",
            sequenceName = "cinema_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cinema_sequence"
    )
    @Column(
            name = "cinema_id",
            updatable = false
    )
    private long id;

    @Column(
            name = "cinema_name"
    )
    private String name;

    private int postcode;

    public Cinema() {
    }

    public Cinema(long id, String name, int postcode) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
    }

    public Cinema(String name, int postcode) {
        this.name = name;
        this.postcode = postcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
