package ftn.upp.invertAuction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JobCategory {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;

    public JobCategory(String name) {
        this.name = name;
    }

    public JobCategory() {
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
}
