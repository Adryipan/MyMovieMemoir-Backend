package com.adrian.mymoviememoirbackend.credential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    @Query("SELECT c FROM Credential c WHERE c.username = :username")
    Optional<Credential> findByUserName(String username);

    @Query("SELECT c FROM Credential c WHERE c.signUpDate = :signupDate")
    Optional<List<Credential>> findBySignUpDate(LocalDate signupDate);
}
