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
}
