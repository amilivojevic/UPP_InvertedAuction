package ftn.upp.invertAuction.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company extends User{

    @OneToOne(cascade = CascadeType.PERSIST)
    private User agent;
    @Column
    private float distance;
    @OneToMany
    private List<JobCategory> categories;

    public Company(long id, String name, String email, String username, String password, String address, String city, String zipcode, User agent, float distance, List<JobCategory> categories) {
        super(id, name, email, username, password, address, city, zipcode);
        this.agent = agent;
        this.distance = distance;
        this.categories = categories;
    }

    public Company(User agent, float distance, List<JobCategory> categories) {
        this.agent = agent;
        this.distance = distance;
        this.categories = categories;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public List<JobCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<JobCategory> categories) {
        this.categories = categories;
    }
}
