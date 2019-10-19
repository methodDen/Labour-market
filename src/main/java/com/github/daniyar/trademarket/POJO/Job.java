package com.github.daniyar.trademarket.POJO;


import kotlin.jvm.internal.markers.KMappedMarker;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "jobId")
    private int id;

    @Column (name = "jobName")
    private String jobName;

    @Column(name = "jobDescription")
    private String jobDescription;

    @Column(name = "paymentSum")
    private String paymentSum;

    @Column(name = "requirements") // list?
    private String requirments;

    @Column(name = "responsibilities")  // list?
    private String responsibilities;

    @Column(name = "workingTerms")   // list?
    private String workingTerms;

    @Column(name = "dateOfCompletion")
    private String dateOfCompletion; // attribute type should be chosen correctly

    @Column(name = "durationInHours")
    private int duration;

    @Column(name = "placeOfPerfomance")
    private String placeOfPerfomance; // google maps???


    @Column(name = "processStatus")
    private String processStatus;       // ENUM??



    @OneToMany // Many to many
    @JoinTable(name = "Job_tag",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(mappedBy = "employers")
    private Set<Employer> employers = new HashSet<>();

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
               Set<Employer> employers, Rating rating) {
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

    public String getRequirments() {
        return requirments;
    }

    public void setRequirments(String requirments) {
        this.requirments = requirments;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getWorkingTerms() {
        return workingTerms;
    }

    public void setWorkingTerms(String workingTerms) {
        this.workingTerms = workingTerms;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public Set<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Set<Employer> employers) {
        this.employers = employers;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
