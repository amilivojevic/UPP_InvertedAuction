package ftn.upp.invertAuction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JobCategory {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;

    public JobCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public JobCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
