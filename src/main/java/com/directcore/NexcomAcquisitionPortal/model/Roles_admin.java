package com.directcore.NexcomAcquisitionPortal.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(schema = "roles")
@JsonIgnoreProperties({"admin" })
public class Roles_admin {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "created_by", nullable = false)
    private String created_by;
    @Column(name = "role", nullable = false)
    private String [] role;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "roles")
    private Set<Admi> admin;
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;



    public Set<Admi> getAdmin() {
        return admin;
    }

    public void setAdmin(Set<Admi> admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String [] getRole() {  return role;    }

    public void setRole(String  [] role) { this.role = role;  }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

}
