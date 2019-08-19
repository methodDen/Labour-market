package POJO;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Employer")
public class Employer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "employerId")
    private int id;

    public Employer() {
    }

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

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Column(name = "companyId")
    private long companyId;         // sensitive data

    @Column(name = "paypalPurse")
    private long creditCardId;

    @Column(name = "employerRole")
    private String employerRole;    // sensitive data

    @Column(name = "phoneNumber")
    private String phoneNumber; // use another type

    //Image

    // foreign keys and relations
    @OneToMany
    @JoinTable(name = "Employer_tags",
            joinColumns = @JoinColumn(name = "employerId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Collection<Tag> tags = new ArrayList<>();

    public int getId() {
        return id;
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


    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getEmployerRole() {
        return employerRole;
    }

    public void setEmployerRole(String employerRole) {
        this.employerRole = employerRole;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    @ManyToMany
    @JoinTable(name = "Employer_company",
            joinColumns = @JoinColumn(name = "employerId"),
            inverseJoinColumns = @JoinColumn(name = "companyId"))
    private Collection<Company> companies = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratingId")
    private Rating rating;


    public Collection<Company> getCompanies() {
        return companies;
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
                ", companyId=" + companyId +
                ", creditCardId=" + creditCardId +
                ", employerRole='" + employerRole + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tags=" + tags +
                ", companies=" + companies +
                ", rating=" + rating +
                '}';
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }
}
