package com.directcore.NexcomAcquisitionPortal.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity // This tells Hibernate to make a table out of this class

public class Login_logs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "adminid", nullable = false)
    private Integer adminid;
    @Column(name = "reponse", nullable = false)
    private String response;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }




    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }
}
