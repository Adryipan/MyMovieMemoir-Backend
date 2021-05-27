package com.adrian.mymoviememoirbackend.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    private final CinemaRepository repository;

    @Autowired
    public CinemaService(CinemaRepository repository) {
        this.repository = repository;
    }

    public Cinema findById(long id) {
        Optional<Cinema> result = repository.findById(id);
        if(result.isEmpty()){
            throw new IllegalStateException("Cinema with id " + id + " does not exist.");
        }
        return result.get();
    }

    public List<Cinema> findByCinemaName(String name) {
        Optional<List<Cinema>> result = repository.findByCinemaName(name);
        if(result.isEmpty()){
            throw new IllegalStateException("Cinema with name " + name + " does not exist.");
        }
        return result.get();
    }

    public List<Cinema> findByPostcode(String postcode) {
        Optional<List<Cinema>> result = repository.findByPostcode(postcode);
        if(result.isEmpty()){
            throw new IllegalStateException("Cinema with postcode " + postcode + " does not exist.");
        }
        return result.get();
    }
}
