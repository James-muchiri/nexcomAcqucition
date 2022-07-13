package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.*;


@Entity
public class Contact_info {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    // contact information
    @Column(name = "management_type")
    private String management_type;
    @Column(name = "full_names")
    private String full_names;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "id_number")
    private String id_number;

    @Column(name = "buildingId")
    private Integer buildingId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
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
}
