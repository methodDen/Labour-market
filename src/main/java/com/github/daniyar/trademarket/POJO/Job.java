package com.github.daniyar.trademarket.POJO;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "jobId")
    private int id;

    @Column (name = "jobName")
    private String jobName;

    @Column(name = "placeOfPerfomance")
    private String placeOfPerfomance; // google maps???

    @Column(name = "dateOfCompletion")
    private String dateOfCompletion; // attribute type should be chosen correctly

    @Column(name = "paymentSum")
    private String paymentSum; // attribute type should be chosen correctly

    @Column(name = "processStatus")
    private String processStatus;       // ENUM??


    @Column(name = "jobDescription")
    private String jobDescription;

    // foreign keys and relations should be implemented
    @OneToMany
    @JoinTable(name = "Job_tag",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Job_employer",
            joinColumns = @JoinColumn(referencedColumnName = "jobId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "employerId"))
    private List<Employer> employers = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName =  "ratingId")
    private Rating rating;

    public Job() {
    }

    public Job(int id, String jobName) {
        this.id = id;
        this.jobName = jobName;
    }

    public Job(int id, String jobName, String placeOfPerfomance, String dateOfCompletion,
               String paymentSum, String processStatus, String jobDescription, List<Tag> tags,
               List<Employer> employers, Rating rating) {
        this.id = id;
        this.jobName = jobName;
        this.placeOfPerfomance = placeOfPerfomance;
        this.dateOfCompletion = dateOfCompletion;
        this.paymentSum = paymentSum;
        this.processStatus = processStatus;
        this.jobDescription = jobDescription;
        this.tags = tags;
        this.employers = employers;
        this.rating = rating;
    }

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

    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
