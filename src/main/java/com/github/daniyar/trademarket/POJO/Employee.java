package com.github.daniyar.trademarket.POJO; // importing packages

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.daniyar.trademarket.Utils.Role;

import javax.persistence.*;
import java.util.*;

@Entity // Database entity jackson annotation
@Table(name="Employee") // Database table Jackson annotation with assigned table name
public class Employee { // Employee class
    @Id // Primary key of Employee table
    @GeneratedValue (strategy = GenerationType.IDENTITY) // using auto-filling for Employee table
    @Column(name = "employeeId") // name of column inside database table
    private int id; // Object parameter equal to entity attribute

    @Column (name = "firstName") // name of column inside database table
    private String firstName; // Object parameter equal to entity attribute

    @Column(name = "lastName") // name of column inside database table
    private String lastName; // Object parameter equal to entity attribute

    @Column(name = "status") // name of column inside database table
    private String status; // Object parameter equal to entity attribute

    @Column(name = "region") // name of column inside database table
    private String regionName; // Object parameter equal to entity attribute

    @Column(name = "profileDescription") // name of column inside database table
    private String profileDescription; // Object parameter equal to entity attribute

    @Column(name = "dateOfBirth") // name of column inside database table
    private String dateOfBirth; // Object parameter equal to entity attribute

    @Column(name = "mobilePhone") // name of column inside database table
    private String mobilePhone;     // Object parameter equal to entity attribute

    @Column(name = "email") // name of column inside database table
    private String email; // Object parameter equal to entity attribute

    @Column(name = "password") // name of column inside database table
    private String password; // Object parameter equal to entity attribute

    @Column(name = "paypalPurse") // name of column inside database table
    private long creditCardId; // Object parameter equal to entity attribute

    @Column (name = "employerUserRole") // name of column inside database table
    private Role role; // Object parameter equal to entity attribute

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER) // annotation displaying relationship between database tables. One-to-many relationship tables should have one column inside one table in which foreign key will be stored
    private Set<Job> jobs = new HashSet<>(); // Object parameter equal to entity attribute


    @JsonIgnore // jackson annotation to ignore error
    @ManyToMany(cascade = CascadeType.ALL) //  annotation displaying relationship between database tables. Many-to-many relationship tables should have one additional table where foreign key from both already existing tables will be stored
    @JoinTable( // annotation to create additional table
            joinColumns = { @JoinColumn(referencedColumnName = "employeeId") },
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "tagId") }
    )
    private List<Tag> tags = new ArrayList<>(); // Object parameter equal to entity attribute


    @OneToOne(cascade = CascadeType.ALL)  // annotation displaying relationship between database tables. One-to-one relationship tables should have additional column inside one table to store foreign key
    @JoinColumn(name = "ratingId") // joinColumn jackson annotation
    private Rating rating; // Object parameter equal to entity attribute


    public Employee() { // empty constructor
    }

    public Employee(int id, String firstName, String lastName, String status,
                    String regionName, String profileDescription, String dateOfBirth,
                    String mobilePhone, String email, String password, long creditCardId,
                    Role role, Set<Job> jobs, List<Tag> tags, Rating rating) { // parametrized constructor

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.regionName = regionName;
        this.profileDescription = profileDescription;
        this.dateOfBirth = dateOfBirth;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.password = password;
        this.creditCardId = creditCardId;
        this.role = role;
        this.jobs = jobs;
        this.tags = tags;
        this.rating = rating;
    }


    // getters and setters
    public int getId() {
        return id;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
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
    public String toString() { // toString method to convert data about Class Object to String
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", regionName='" + regionName + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creditCardId=" + creditCardId +
                ", role=" + role +
                ", jobs=" + jobs +
                ", tags=" + tags +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) { // equals method
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                creditCardId == employee.creditCardId &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(status, employee.status) &&
                Objects.equals(regionName, employee.regionName) &&
                Objects.equals(profileDescription, employee.profileDescription) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(mobilePhone, employee.mobilePhone) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(password, employee.password) &&
                role == employee.role &&
                Objects.equals(jobs, employee.jobs) &&
                Objects.equals(tags, employee.tags) &&
                Objects.equals(rating, employee.rating);
    }

    @Override
    public int hashCode() { // hashcode method
        return Objects.hash(id, firstName, lastName, status, regionName, profileDescription, dateOfBirth, mobilePhone, email, password, creditCardId, role, jobs, tags, rating);
    }
}
