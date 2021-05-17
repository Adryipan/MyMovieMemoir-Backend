/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieMenoirws;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByUserId", query = "SELECT p FROM Person p WHERE p.userId = :userId")
    , @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE UPPER(p.firstName) = UPPER(:firstName)")
    , @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE UPPER(p.surname) = UPPER(:surname)")
    , @NamedQuery(name = "Person.findByGender", query = "SELECT p FROM Person p WHERE UPPER(p.gender) = UPPER(:gender)")
    , @NamedQuery(name = "Person.findByDob", query = "SELECT p FROM Person p WHERE p.dob = :dob")
    , @NamedQuery(name = "Person.findByStreetName", query = "SELECT p FROM Person p WHERE UPPER(p.streetAddress) = UPPER(:streetAddress)")
    , @NamedQuery(name = "Person.findByStateCode", query = "SELECT p FROM Person p WHERE UPPER(p.stateCode) = UPPER(:stateCode)")
    , @NamedQuery(name = "Person.findByPostcode", query = "SELECT p FROM Person p WHERE p.postcode = :postcode")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SURNAME")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENDER")
    private Character gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 50)
    @Column(name = "STREET_ADDRESS")
    private String streetAddress;
    @Size(max = 3)
    @Column(name = "STATE_CODE")
    private String stateCode;
    @Size(max = 4)
    @Column(name = "POSTCODE")
    private String postcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Credentials> credentialsCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Memoir> memoirCollection;

    public Person() {
    }

    public Person(Integer userId) {
        this.userId = userId;
    }

    public Person(Integer userId, String firstName, String surname, Character gender, Date dob) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @XmlTransient
    public Collection<Credentials> getCredentialsCollection() {
        return credentialsCollection;
    }

    public void setCredentialsCollection(Collection<Credentials> credentialsCollection) {
        this.credentialsCollection = credentialsCollection;
    }

    @XmlTransient
    public Collection<Memoir> getMemoirCollection() {
        return memoirCollection;
    }

    public void setMemoirCollection(Collection<Memoir> memoirCollection) {
        this.memoirCollection = memoirCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "movieMenoirws.Person[ userId=" + userId + " ]";
    }
    
}
