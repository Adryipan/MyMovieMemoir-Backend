package com.adrian.mymoviememoirbackend.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "api/v1/cinema")
public class CinemaController {

    private final CinemaService service;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.service = cinemaService;
    }

    @GetMapping(path = "/{id}")
    public Cinema findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @GetMapping(path = "/findByCinemaName/{name}")
    public List<Cinema> findByCinemaName(@PathVariable("name") String name){
        return service.findByCinemaName(name.replace("-", " "));
    }

    @GetMapping(path = "findByPostcode/{postcode}")
    public List<Cinema> findByPostcode(@PathVariable("postcode") String postcode){
        String regex = "^\\d{4}";
        Pattern postCodePattern = Pattern.compile(regex);
        if(postCodePattern.matcher(postcode).matches()) {
            return service.findByPostcode(postcode);
        }else{
            throw new IllegalArgumentException("Invalid postcode");
        }
    }
}
