package com.adrian.mymoviememoirbackend.cinema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query("SELECT c FROM Cinema c WHERE UPPER(c.name) = UPPER(:name)")
    Optional<List<Cinema>> findByCinemaName(String name);

    @Query("SELECT c FROM Cinema c WHERE UPPER(c.postcode) = UPPER(:postcode)")
    Optional<List<Cinema>> findByPostcode(int postcode);
}
