package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.*;

@Entity
public class Building_info {

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
    @Column(name = "use_type")
    private String use_type;
    @Column(name = "street_name")
    private String street_name;
    @Column(name = "power")
    private String power;
    @Column(name = "state")
    private String state;
    @Column(name = "RoA")
    private String RoA;
    @Column(name = "ToA")
    private String ToA;
    @Column(name = "security")
    private String security;
    @Column(name = "comments")
    private String comments;



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


    public String getUse_type() {
        return use_type;
    }

    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRoA() {
        return RoA;
    }

    public void setRoA(String roA) {
        RoA = roA;
    }

    public String getToA() {
        return ToA;
    }

    public void setToA(String toA) {
        ToA = toA;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
