package com.github.daniyar.trademarket.POJO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.daniyar.trademarket.Utils.Role;

import javax.persistence.*;
import java.util.*;

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

    @Column (name = "employerUserRole")
    private Role role;


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

    public Employer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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


    public Employer(int id, String firstName, String lastName, String region,
                    String dateOfBirth, String email, String password, String phoneNumber,
                    long paypalPurse, String profileDescription, String employerRole, Role role,
                    Set<Job> jobs, List<Tag> tags, Company company, Rating rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.paypalPurse = paypalPurse;
        this.profileDescription = profileDescription;
        this.employerRole = employerRole;
        this.role = role;
        this.jobs = jobs;
        this.tags = tags;
        this.company = company;
        this.rating = rating;
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


    public long getPaypalPurse() {
        return paypalPurse;
    }

    public void setPaypalPurse(long paypalPurse) {
        this.paypalPurse = paypalPurse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return id == employer.id &&
                paypalPurse == employer.paypalPurse &&
                Objects.equals(firstName, employer.firstName) &&
                Objects.equals(lastName, employer.lastName) &&
                Objects.equals(region, employer.region) &&
                Objects.equals(dateOfBirth, employer.dateOfBirth) &&
                Objects.equals(email, employer.email) &&
                Objects.equals(password, employer.password) &&
                Objects.equals(phoneNumber, employer.phoneNumber) &&
                Objects.equals(profileDescription, employer.profileDescription) &&
                Objects.equals(employerRole, employer.employerRole) &&
                role == employer.role &&
                Objects.equals(jobs, employer.jobs) &&
                Objects.equals(tags, employer.tags) &&
                Objects.equals(company, employer.company) &&
                Objects.equals(rating, employer.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, region, dateOfBirth, email, password, phoneNumber, paypalPurse, profileDescription, employerRole, role, jobs, tags, company, rating);
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
                ", role=" + role +
                ", jobs=" + jobs +
                ", tags=" + tags +
                ", company=" + company +
                ", rating=" + rating +
                '}';
    }
}
