package ftn.upp.invertAuction.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User client;

    @ManyToOne
    private JobCategory jobCategory;

    @Column
    private double maxPrice;

    @Column
    private String jobDescription;

    @Column
    private Date jobApplicationDeadline;

    @Column
    private Date jobDeadline;

    @Column
    private int minNumberOfCandidates;

    @Column
    private int maxNumberOfCandidate;

    @Column
    private String procInstId;


    public Request() {
    }

    public Request(User client, JobCategory jobCategory, double maxPrice, String jobDescription, Date jobApplicationDeadline, Date jobDeadline, int minNumberOfCandidates, int maxNumberOfCandidate, String procInstId) {
        this.client = client;
        this.jobCategory = jobCategory;
        this.maxPrice = maxPrice;
        this.jobDescription = jobDescription;
        this.jobApplicationDeadline = jobApplicationDeadline;
        this.jobDeadline = jobDeadline;
        this.minNumberOfCandidates = minNumberOfCandidates;
        this.maxNumberOfCandidate = maxNumberOfCandidate;
        this.procInstId = procInstId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", clientId=" + client.getId() +
                ", jobCategoryName=" + jobCategory.getName() +
                ", maxPrice=" + maxPrice +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobApplicationDeadline=" + jobApplicationDeadline +
                ", jobDeadline=" + jobDeadline +
                ", minNumberOfCandidates=" + minNumberOfCandidates +
                ", maxNumberOfCandidate=" + maxNumberOfCandidate +
                ", procInstId='" + procInstId + '\'' +
                '}';
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getJobApplicationDeadline() {
        return jobApplicationDeadline;
    }

    public void setJobApplicationDeadline(Date jobApplicationDeadline) {
        this.jobApplicationDeadline = jobApplicationDeadline;
    }

    public Date getJobDeadline() {
        return jobDeadline;
    }

    public void setJobDeadline(Date jobDeadline) {
        this.jobDeadline = jobDeadline;
    }

    public int getMinNumberOfCandidates() {
        return minNumberOfCandidates;
    }

    public void setMinNumberOfCandidates(int minNumberOfCandidates) {
        this.minNumberOfCandidates = minNumberOfCandidates;
    }

    public int getMaxNumberOfCandidate() {
        return maxNumberOfCandidate;
    }

    public void setMaxNumberOfCandidate(int maxNumberOfCandidate) {
        this.maxNumberOfCandidate = maxNumberOfCandidate;
    }
}
