package ftn.upp.invertAuction.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Company company;

    @ManyToOne
    private JobCategory jobCategory;

    @Column
    private double maxPrice;

    @Column
    private Date jobApplicationDeadline;

    @Column
    private Date jobDeadline;

    @Column
    private int minNumberOfCandidates;

    @Column
    private int maxNumberOfCandidate;

    public Request(Company company, JobCategory jobCategory, double maxPrice, Date jobApplicationDeadline, Date jobDeadline, int minNumberOfCandidates, int maxNumberOfCandidate) {
        this.company = company;
        this.jobCategory = jobCategory;
        this.maxPrice = maxPrice;
        this.jobApplicationDeadline = jobApplicationDeadline;
        this.jobDeadline = jobDeadline;
        this.minNumberOfCandidates = minNumberOfCandidates;
        this.maxNumberOfCandidate = maxNumberOfCandidate;

    }

    public Request() {
    }

    @Override
    public String toString() {
        return "Request{" +
                "companyId=" + company.getId() +
                ", jobCategoryName=" + jobCategory.getName() +
                ", maxPrice=" + maxPrice +
                ", jobApplicationDeadline=" + jobApplicationDeadline +
                ", jobDeadline=" + jobDeadline +
                ", minNumberOfCandidates=" + minNumberOfCandidates +
                ", maxNumberOfCandidate=" + maxNumberOfCandidate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
