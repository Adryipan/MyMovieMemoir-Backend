package com.adrian.mymoviememoirbackend.credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CredentialService {

    @Autowired
    private final CredentialRepository repository;

    public CredentialService(CredentialRepository repository) {
        this.repository = repository;
    }

    public Credential findById(long id) {
        Optional<Credential> result = this.repository.findById(id);
        if(result.isEmpty()){
            throw new IllegalStateException("Credential with id " + id + " does not exist.");
        }
        return result.get();
    }

    public Credential findByUserName(String username) {
        Optional<Credential> result = this.repository.findByUserName(username);
        if(result.isEmpty()){
            throw new IllegalStateException("Credential with username " + username + " does not exist.");
        }
        return result.get();
    }

    public List<Credential> findBySignUpDate(String signUpDate) {
        String[] dateString = signUpDate.split("-");
        Optional<List<Credential>> result = this.repository.findBySignUpDate(
                LocalDate.of(
                        Integer.parseInt(dateString[0]),
                        Integer.parseInt(dateString[1]),
                        Integer.parseInt(dateString[2])
                )
        );
        if(result.isEmpty()){
            throw new IllegalStateException("Credential with signup date " + signUpDate + " does not exist.");
        }
        return result.get();
    }
}
