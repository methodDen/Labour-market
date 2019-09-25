package com.github.daniyar.trademarket.POJO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId")
    private int ratingId;

    @Column(name = "ratingOfEmployee")
    private int ratingOfEmployee; // somehow connect POST of Employee with this property

    @Column(name = "ratingOfEmployer")
    private int ratingOfEmployer;

    @Column(name = "commentOfEmployee")
    private String commentOfEmployee;

    @Column(name = "commentOfEmployer")
    private String commentOfEmployer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "jobId")
    private Job job;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "employeeId")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "employerId")
    private Employer employer;

    public Rating() {
    }

    public Rating(int ratingId) {
        this.ratingId = ratingId;
    }

    public Rating(int ratingId, int ratingOfEmployee, int ratingOfEmployer, String commentOfEmployee, String commentOfEmployer, Job job, Employee employee, Employer employer) {
        this.ratingId = ratingId;
        this.ratingOfEmployee = ratingOfEmployee;
        this.ratingOfEmployer = ratingOfEmployer;
        this.commentOfEmployee = commentOfEmployee;
        this.commentOfEmployer = commentOfEmployer;
        this.job = job;
        this.employee = employee;
        this.employer = employer;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingOfEmployee() {
        return ratingOfEmployee;
    }

    public void setRatingOfEmployee(int ratingOfEmployee) {
        this.ratingOfEmployee = ratingOfEmployee;
    }

    public int getRatingOfEmployer() {
        return ratingOfEmployer;
    }

    public void setRatingOfEmployer(int ratingOfEmployer) {
        this.ratingOfEmployer = ratingOfEmployer;
    }

    public String getCommentOfEmployee() {
        return commentOfEmployee;
    }

    public void setCommentOfEmployee(String commentOfEmployee) {
        this.commentOfEmployee = commentOfEmployee;
    }

    public String getCommentOfEmployer() {
        return commentOfEmployer;
    }

    public void setCommentOfEmployer(String commentOfEmployer) {
        this.commentOfEmployer = commentOfEmployer;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

}
