package ftn.upp.invertAuction.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private float distance;

    @ManyToMany
    private List<JobCategory> jobCategories;

    @ManyToOne
    private User agent;

    public Company(String name, String email, String address, float distance, List<JobCategory> jobCategories, User agent) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.distance = distance;
        this.jobCategories = jobCategories;
        this.agent = agent;
    }

    public Company() {
    }

    public List<JobCategory> getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(List<JobCategory> jobCategories) {
        this.jobCategories = jobCategories;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
