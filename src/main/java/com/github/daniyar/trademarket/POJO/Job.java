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
    private String requirements;

    @Column(name = "responsibilities")  // list?
    private String responsibilities;

    @Column(name = "workingTerms")   // list?
    private String workingTerms;

    @Column(name = "dateOfCompletion")
    private String dateOfCompletion; // attribute type should be chosen correctly

    @Column(name = "durationInHours")
    private int duration;

    @Column(name = "placeOfPerformance")
    private String placeOfPerformance; // google maps???


    @Column(name = "processStatus")
    private String processStatus;       // ENUM??



    @OneToMany // Many to many
    @JoinTable(name = "Job_tag",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<Tag> tags = new ArrayList<>();


    @ManyToMany(mappedBy = "jobs", fetch = FetchType.EAGER) // mappedBy - name of jobs Set in Employer Class + fetch type
    private Set<Employer> employers = new HashSet<>();

    @ManyToOne
    @JoinColumn(referencedColumnName ="employeeId") // tested
    private Employee employee;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName =  "ratingId")
    private Rating rating;

    public Job() {
    }

    public Job(int id, String jobName, String jobDescription, String paymentSum,
               String requirements, String responsibilities, String workingTerms,
               String dateOfCompletion, int duration, String placeOfPerformance, String processStatus,
               List<Tag> tags, Set<Employer> employers, Rating rating) {
        this.id = id;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.paymentSum = paymentSum;
        this.requirements = requirements;
        this.responsibilities = responsibilities;
        this.workingTerms = workingTerms;
        this.dateOfCompletion = dateOfCompletion;
        this.duration = duration;
        this.placeOfPerformance = placeOfPerformance;
        this.processStatus = processStatus;
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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPlaceOfPerformance() {
        return placeOfPerformance;
    }

    public void setPlaceOfPerformance(String placeOfPerformance) {
        this.placeOfPerformance = placeOfPerformance;
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
