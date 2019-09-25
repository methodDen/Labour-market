package com.github.daniyar.trademarket.POJO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employer")
public class Employer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "employerId")
    private int id;

    @Column (name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "region")
    private String region;

    @Column(name = "email")
    private String email;       // sensitive data

    @Column(name = "extraEmail")
    private String extraEmail;  // sensitive data

    @Column(name = "password")
    private String password; // sensitive data, cypher


    @Column(name = "profileDescription")
    private String profileDescription;

    @Column(name = "paypalPurse")
    private long creditCardId;

    @Column(name = "employerRole")
    private String employerRole;    // sensitive data
    @Column(name = "phoneNumber")
    private String phoneNumber; // use another type



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = { @JoinColumn(referencedColumnName = "employerId") },
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "tagId") }
    )
    private List<Tag> tags = new ArrayList<>();

    // unserialized
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(referencedColumnName ="companyId")
    private Company company;

    // unserialized
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "ratingId")
    private Rating rating;

    public Employer() {
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password,
                    String profileDescription, long creditCardId,
                    String employerRole, String phoneNumber, List<Tag> tags, Company company, Rating rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.extraEmail = extraEmail;
        this.password = password;
        this.profileDescription = profileDescription;
        this.creditCardId = creditCardId;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
        this.tags = tags;
        this.company = company;
        this.rating = rating;
    }

    public Employer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password, String profileDescription, long creditCardId,
                    String employerRole, String phoneNumber, List<Tag> tags) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.extraEmail = extraEmail;
        this.password = password;
        this.profileDescription = profileDescription;
        this.creditCardId = creditCardId;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password, String profileDescription, long creditCardId, String employerRole, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.extraEmail = extraEmail;
        this.password = password;
        this.profileDescription = profileDescription;
        this.creditCardId = creditCardId;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password, String profileDescription, long creditCardId, String employerRole, String phoneNumber, Company company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.extraEmail = extraEmail;
        this.password = password;
        this.profileDescription = profileDescription;
        this.creditCardId = creditCardId;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getExtraEmail() {
        return extraEmail;
    }

    public void setExtraEmail(String extraEmail) {
        this.extraEmail = extraEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public long getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(long companyId) {
//        this.companyId = companyId;
//    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmployerRole() {
        return employerRole;
    }

    public void setEmployerRole(String employerRole) {
        this.employerRole = employerRole;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", region='" + region + '\'' +
                ", email='" + email + '\'' +
                ", extraEmail='" + extraEmail + '\'' +
                ", password='" + password + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", creditCardId=" + creditCardId +
                ", employerRole='" + employerRole + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tags=" + tags +
                ", company=" + company +
                ", rating=" + rating +
                '}';
    }

}
