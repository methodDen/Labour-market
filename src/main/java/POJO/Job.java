package POJO;

import POJO.Employer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "POJO.Job")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "jobId")
    private int id;

    @Override
    public String toString() {
        return "POJO.Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentSum='" + paymentSum + '\'' +
                ", timeDuration=" + timeDuration +
                ", processInfo='" + processInfo + '\'' +
                ", rating=" + rating +
                ", tags=" + tags +
                ", employers=" + employers +
                '}';
    }

    @Column (name = "jobName")
    private String jobName;

    @Column(name = "dateOfCompletion")
    private String lastName; // attribute type should be chosen correctly

    @Column(name = "paymentSum")
    private String paymentSum; // attribute type should be chosen correctly

    @Column(name = "timeDuration")
    private int timeDuration;     // attribute type should be chosen correctly

    @Column(name = "processInfo")
    private String processInfo;

    @Column(name = "rating")
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getProcessInfo() {
        return processInfo;
    }

    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public Collection<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Collection<Employer> employers) {
        this.employers = employers;
    }

    // foreign keys and relations should be implemented
    @OneToMany
    @JoinTable(name = "Job_tag",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Collection<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Job_employer",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "employerId"))
    private Collection<Employer> employers = new ArrayList<>();

    public Job() {
    }
}
