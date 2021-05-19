package com.adrian.mymoviememoirbackend.credential;

import com.adrian.mymoviememoirbackend.Person.Person;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Credential")
@Table(
        name="Credential",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique", columnNames = "username")
        }

)
public class Credential {

        @Id
        @Column(name = "user_id")
        private long id;

        @OneToOne
        @MapsId
        @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "person_credential_FK"))
        private Person person;

        @Column(
                name = "username"
        )
        private String username;

        private String password;

        @Column(
                name = "sign_up_date"
        )
        private LocalDate signUpDate;

        public Credential() {
        }

        public Credential(long id, Person person, String username, String password, LocalDate signUpDate) {
                this.id = id;
                this.person = person;
                this.username = username;
                this.password = password;
                this.signUpDate = signUpDate;
        }

        public Credential(Person person, String username, String password, LocalDate signUpDate) {
                this.person = person;
                this.username = username;
                this.password = password;
                this.signUpDate = signUpDate;
        }

        public Person getPerson() {
                return person;
        }

        public void setPerson(Person person) {
                this.person = person;
        }

        public LocalDate getSignUpDate() {
                return signUpDate;
        }

        public void setSignUpDate(LocalDate signUpDate) {
                this.signUpDate = signUpDate;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public Person getUser() {
                return person;
        }

        public void setUser(Person person) {
                this.person = person;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}
