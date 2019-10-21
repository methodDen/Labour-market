package com.github.daniyar.trademarket.POJO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "email")
    private String email;       // sensitive data

    @Column(name = "password")
    private String password; // sensitive data, cypher

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "paypalPurse")
    private long paypalPurse;

    @Column(name = "profileDescription")
    private String profileDescription;

    @Column(name = "employerRole")
    private String employerRole;    // sensitive data


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // testing
    @JoinTable(
            name = "Employer_Job",
            joinColumns = {@JoinColumn(name = "employerId")},
            inverseJoinColumns = {@JoinColumn(name = "jobId")}
    )
    Set<Job> jobs = new HashSet<>();



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL) // toTest
    @JoinTable(
            joinColumns = { @JoinColumn(referencedColumnName = "employerId") },
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "tagId") }
    )
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(referencedColumnName ="companyId") // tested
    private Company company;


    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL) // toTest
    @JoinColumn(referencedColumnName = "ratingId")
    private Rating rating;

    public Employer() {
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password,
                    String profileDescription, long paypalPurse,
                    String employerRole, String phoneNumber, List<Tag> tags, Company company, Rating rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.paypalPurse = paypalPurse;
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

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password, String profileDescription, long paypalPurse,
                    String employerRole, String phoneNumber, List<Tag> tags) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.paypalPurse = paypalPurse;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password, String profileDescription, long paypalPurse, String employerRole, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.paypalPurse = paypalPurse;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
    }

    public Employer(int id, String firstName, String lastName, String region, String email, String extraEmail, String password, String profileDescription, long paypalPurse, String employerRole, String phoneNumber, Company company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.paypalPurse = paypalPurse;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public Employer(int id, String firstName, String lastName, String region, String email,
                    String password, String profileDescription, long paypalPurse, String employerRole,
                    String phoneNumber, Set<Job> jobs, List<Tag> tags, Company company, Rating rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.paypalPurse = paypalPurse;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
        this.jobs = jobs;
        this.tags = tags;
        this.company = company;
        this.rating = rating;
    }

    public Employer(int id, String firstName, String lastName, String region, String dateOfBirth, String email, String password, String phoneNumber, long paypalPurse, String profileDescription, String employerRole, Set<Job> jobs, List<Tag> tags, Company company, Rating rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.paypalPurse = paypalPurse;
        this.dateOfBirth = dateOfBirth;
        this.employerRole = employerRole;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.jobs = jobs;
        this.company = company;
        this.tags = tags;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getCreditCardId() {
        return paypalPurse;
    }

    public void setCreditCardId(long paypalPurse) {
        this.paypalPurse = paypalPurse;
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


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
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
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", paypalPurse=" + paypalPurse +
                ", profileDescription='" + profileDescription + '\'' +
                ", employerRole='" + employerRole + '\'' +
                ", jobs=" + jobs +
                ", tags=" + tags +
                ", company=" + company +
                ", rating=" + rating +
                '}';
    }
}
