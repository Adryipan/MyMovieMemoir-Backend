package com.adrian.mymoviememoirbackend.credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/credential")
public class CredentialController {

    @Autowired
    private final CredentialService service;

    public CredentialController(CredentialService service) {
        this.service = service;
    }

    @GetMapping(path = "{id}")
    public Credential findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    //Get by username
    @GetMapping(path = "findByUsername/{username}")
    public Credential findByUsername(@PathVariable("username") String username){
        return service.findByUserName(username);
    }

    //Get by signup date
    @GetMapping(path = "findBySignUpDate/{signUpDate}")
    public List<Credential> findBySignUpDate(@PathVariable("signUpDate") String signUpDate){
        return service.findBySignUpDate(signUpDate);
    }

//    @PostMapping(path = "/session")
//    public ResponseEntity login(){
//
//    }
//
//    @DeleteMapping(path = "/session")
//    public ResponseEntity logout(){
//
//    }

//    @PostMapping(path = "/register")
//    public ResponseEntity register(PersonCredential personCredential){
//
//    }



}
