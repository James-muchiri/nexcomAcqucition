package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.*;


@Entity
public class Contact_profile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /// contact profile
    @Column(name = "management_type")
    private String         management_type;
    @Column(name = "full_names")
    private String        full_names;
    @Column(name = "phone_number")
    private String       phone_number;
    @Column(name = "id_number")
    private String         id_number;
    @Column(name = "email")
    private String          email;
    @Column(name = "AccessRights")
    private String        AccessRights;
    @Column(name = "AccessRights_text")
    private String        AccessRights_text;
    @Column(name = "roa")
    private String        roa;
    @Column(name = "roa_status")
    private String       roa_status;
    @Column(name = "Access_Status")
    private String             Access_Status;
    @Column(name = "Other_terms")
    private String          Other_terms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManagement_type() {
        return management_type;
    }

    public void setManagement_type(String management_type) {
        this.management_type = management_type;
    }

    public String getFull_names() {
        return full_names;
    }

    public void setFull_names(String full_names) {
        this.full_names = full_names;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessRights() {
        return AccessRights;
    }

    public void setAccessRights(String accessRights) {
        AccessRights = accessRights;
    }

    public String getAccessRights_text() {
        return AccessRights_text;
    }

    public void setAccessRights_text(String accessRights_text) {
        AccessRights_text = accessRights_text;
    }

    public String getRoa() {
        return roa;
    }

    public void setRoa(String roa) {
        this.roa = roa;
    }

    public String getRoa_status() {
        return roa_status;
    }

    public void setRoa_status(String roa_status) {
        this.roa_status = roa_status;
    }

    public String getAccess_Status() {
        return Access_Status;
    }

    public void setAccess_Status(String access_Status) {
        Access_Status = access_Status;
    }

    public String getOther_terms() {
        return Other_terms;
    }

    public void setOther_terms(String other_terms) {
        Other_terms = other_terms;
    }
}
