package com.directcore.NexcomAcquisitionPortal.model;


import javax.persistence.*;

@Entity
public class Building_information {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    // building location
    @Column(name = "region")
    private String region;
    @Column(name = "zone")
    private String zone;
    @Column(name = "area")
    private String area;
    @Column(name = "cluster")
    private String cluster;

    // building information
    @Column(name = "building_name")
    private String building_name;
    @Column(name = "building_description")
    private String building_description;
    @Column(name = "building_type")
    private String building_type;
    @Column(name = "possible_sales")
    private String possible_sales;
    @Column(name = "building_photos")
    private String building_photos;

    // contact information
    @Column(name = "management_type")
    private String management_type;
    @Column(name = "full_names")
    private String full_names;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "id_number")
    private String id_number;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }


    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }
    public String getBuilding_description() {
        return building_description;
    }

    public void setBuilding_description(String building_description) {
        this.building_description = building_description;
    }
    public String getBuilding_type() {
        return building_type;
    }

    public void setBuilding_type(String building_type) {
        this.building_type = building_type;
    }

    public String getPossible_sales() {
        return possible_sales;
    }

    public void setPossible_sales(String possible_sales) {
        this.possible_sales = possible_sales;
    }


    public String getBuilding_photos() {
        return building_photos;
    }

    public void setBuilding_photos(String building_photos) {
        this.building_photos = building_photos;
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
