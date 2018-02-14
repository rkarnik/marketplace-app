package com.intuit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Project implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "uuid", nullable = false)
    public String uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private User seller;

    private String sellerUuid;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "description", nullable = false)
    public String description;

    @Column(name = "project_bid_status")
    public String projectBidStatus;

    @Column(name = "minimum_bid_value")
    public double minimumBidValue=0;

    @Column(name = "max_budget")
    public double maxBudget=0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline_time")
    private Date deadLineTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private Date updatedOn;

    @PrePersist
    public void prePersist() {
        Date dt = new Date();
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            this.createdOn = dt;
            this.updatedOn = dt;
        } else {
            this.updatedOn = dt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        Date dt = new Date();
        this.updatedOn = dt;
    }

}
