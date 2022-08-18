package com.directcore.NexcomAcquisitionPortal.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class PasswordResetToken {



    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Admi.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Admi user;

    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Admi getUser() {
        return user;
    }

    public void setUser(Admi user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
