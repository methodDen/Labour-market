package POJO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="POJO.Employee")
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

    @Column(name = "email")
    private String email;

    @Column(name = "paypalPurse")
    private long creditCardId;

    // add foreign keys and establish table relations
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Employee_Job",
            joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "jobId"))
    private Collection<Job> jobs = new ArrayList<>();

    public Collection<Tag> getTags() {

        return tags;
    }

    @Override
    public String toString() {
        return "POJO.Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", email='" + email + '\'' +
                ", creditCardId=" + creditCardId +
                ", jobs=" + jobs +
                ", tags=" + tags +
                '}';
    }

    public Employee() {
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
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

    public Collection<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Collection<Job> jobs) {
        this.jobs = jobs;
    }
    @OneToMany
    @JoinTable (name = "Employee_Tag",
            joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Collection<Tag> tags = new ArrayList<>();
}
