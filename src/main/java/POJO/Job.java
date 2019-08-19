package POJO;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "jobId")
    private int id;

    public String getPlaceOfPerfomance() {
        return placeOfPerfomance;
    }

    public void setPlaceOfPerfomance(String placeOfPerfomance) {
        this.placeOfPerfomance = placeOfPerfomance;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Column (name = "jobName")
    private String jobName;

    @Column(name = "placeOfPerfomance")
    private String placeOfPerfomance; // google maps???

    @Column(name = "dateOfCompletion")
    private String dateOfCompletion; // attribute type should be chosen correctly

    @Column(name = "paymentSum")
    private String paymentSum; // attribute type should be chosen correctly

    @Column(name = "processStatus")
    private String processStatus;


    @Column(name = "jobDescription")
    private String jobDescription;

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



    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratingId")
    private Rating rating;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", placeOfPerfomance='" + placeOfPerfomance + '\'' +
                ", dateOfCompletion='" + dateOfCompletion + '\'' +
                ", paymentSum='" + paymentSum + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", tags=" + tags +
                ", employers=" + employers +
                ", rating=" + rating +
                '}';
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Job() {
    }
}
