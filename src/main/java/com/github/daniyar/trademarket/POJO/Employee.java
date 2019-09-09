package com.github.daniyar.trademarket.POJO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int id;

    @Column (name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "region")
    private String regionName;

    @Column(name = "mobilePhone")
    private String mobilePhone;     //Perhaps other type would suit for it better

    @Column(name = "profileDescription")
    private String profileDescription;

    @Column(name = "password")
    private String password; // sensitive data, cypher

    @Column(name = "email")
    private String email;

    @Column(name = "extraEmail")
    private String extraEmail;  // sensitive data

    @Column(name = "paypalPurse")
    private long creditCardId;

    // add foreign keys and establish table relations
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Employee_Job",
            joinColumns = @JoinColumn(referencedColumnName = "employeeId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "jobId"))
    private List<Job> jobs = new ArrayList<>();

    @OneToMany
    @JoinTable (name = "Employee_Tag",
            joinColumns = @JoinColumn(referencedColumnName = "employeeId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "tagId"))
    private List<Tag> tags = new ArrayList<>();




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratingId")
    private Rating rating;


    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String regionName, String mobilePhone, String profileDescription, String password, String email, String extraEmail, long creditCardId, List<Job> jobs, List<Tag> tags, Rating rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regionName = regionName;
        this.mobilePhone = mobilePhone;
        this.profileDescription = profileDescription;
        this.password = password;
        this.email = email;
        this.extraEmail = extraEmail;
        this.creditCardId = creditCardId;
        this.jobs = jobs;
        this.tags = tags;
        this.rating = rating;
    }

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

    public String getExtraEmail() {
        return extraEmail;
    }

    public void setExtraEmail(String extraEmail) {
        this.extraEmail = extraEmail;
    }

    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
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
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", extraEmail='" + extraEmail + '\'' +
                ", creditCardId=" + creditCardId +
                ", jobs=" + jobs +
                ", tags=" + tags +
                ", rating=" + rating +
                '}';
    }
}
