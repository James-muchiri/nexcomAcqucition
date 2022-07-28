package com.directcore.NexcomAcquisitionPortal.model;


import javax.persistence.*;

@Entity
public class Access_right_profile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


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
    // building profile
    @Column(name = "buildingcode")
    private String buildingcode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBuildingcode() {
        return buildingcode;
    }

    public void setBuildingcode(String buildingcode) {
        this.buildingcode = buildingcode;
    }
}
