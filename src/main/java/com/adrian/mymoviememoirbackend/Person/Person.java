package com.adrian.mymoviememoirbackend.user;

import com.adrian.mymoviememoirbackend.credential.Credential;
import com.adrian.mymoviememoirbackend.memoir.Memoir;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "User")
@Table(
        name="User"
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private long user_id;

    @Column(
            name = "f_name"
    )
    private String firstName;

    @Column(
            name = "surname"
    )
    private String surname;

    @Column(
            name = "gender"
    )
    private String gender;

    @Column(
            name = "dob",
            columnDefinition = "DATE"
    )
    private LocalDate dob;

    @Column(
            name = "street_address"
    )
    private String streetAddress;

    @Column(
            name = "state_code"
    )
    private String stateCode;

    @Column(
            name = "postcode"
    )
    private int postcode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Credential credential;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Memoir> memoirs;


    public User() {
    }

    public User(long user_id, String firstName, String surname, String gender, LocalDate dob, String streetAddress, String stateCode, int postcode) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
        this.streetAddress = streetAddress;
        this.stateCode = stateCode;
        this.postcode = postcode;
    }

    public User(String firstName, String surname, String gender, LocalDate dob, String streetAddress, String stateCode, int postcode) {
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
        this.streetAddress = streetAddress;
        this.stateCode = stateCode;
        this.postcode = postcode;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", streetAddress='" + streetAddress + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
