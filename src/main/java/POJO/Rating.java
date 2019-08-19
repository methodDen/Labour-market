package POJO;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId")
    private int ratingId;

    @Column(name = "ratingOfEmployee")
    private int ratingOfEmployee;

    @Column(name = "ratingOfEmployer")
    private int ratingOfEmployer;

    @Column(name = "commentOfEmployee")
    private String commentOfEmployee;

    @Column(name = "commentOfEmployer")
    private String commentOfEmployer;

    public Rating() {
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

    @OneToOne(mappedBy = "rating")
    private Job job;

    @OneToOne(mappedBy = "rating")
    private Employee employee;

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @OneToOne(mappedBy = "rating")
    private Employer employer;

    public Job getJob() {
        return job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", ratingOfEmployee=" + ratingOfEmployee +
                ", ratingOfEmployer=" + ratingOfEmployer +
                ", commentOfEmployee='" + commentOfEmployee + '\'' +
                ", commentOfEmployer='" + commentOfEmployer + '\'' +
                ", job=" + job +
                ", employee=" + employee +
                ", employer=" + employer +
                '}';
    }
}
