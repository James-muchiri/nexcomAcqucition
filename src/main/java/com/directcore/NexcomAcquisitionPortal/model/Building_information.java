package com.directcore.NexcomAcquisitionPortal.model;


import javax.persistence.*;


public class Building_information {



    // building location
    @Column(name = "region")
    private Integer region;
    @Column(name = "zone")
    private Integer zone;
    @Column(name = "area")
    private Integer area;
    @Column(name = "cluster")
    private Integer cluster;

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

    // contact information
    @Column(name = "management_type")
    private String management_type;
    @Column(name = "full_names")
    private String full_names;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "id_number")
    private String id_number;
    @Column(name = "email")
    private String email;


    // sales profile

    @Column(name = "numberofUnits")
    private String NumberofUnits;
    @Column(name = "packagesPosible")
    private String packagesPosible;
    @Column(name = "rent")
    private String rent;
    @Column(name = "exsistingProviders")
    private String exsistingProviders;
    @Column(name = "internetUsers")
    private String internetUsers;


    @Column(name = "blocks")
    private String blocks;
    @Column(name = "floors")
    private String floors;


    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }



    public String getUse_type() {
        return use_type;
    }

    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }



    public Integer getRegion() {
        return region;
    }
    public void setRegion(Integer region) {
        this.region = region;
    }


    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCluster() {
        return cluster;
    }

    public void setCluster(Integer cluster) {
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

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
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

    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberofUnits() {
        return NumberofUnits;
    }

    public void setNumberofUnits(String numberofUnits) {
        NumberofUnits = numberofUnits;
    }

    public String getPackagesPosible() {
        return packagesPosible;
    }

    public void setPackagesPosible(String packagesPosible) {
        this.packagesPosible = packagesPosible;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getExsistingProviders() {
        return exsistingProviders;
    }

    public void setExsistingProviders(String exsistingProviders) {
        this.exsistingProviders = exsistingProviders;
    }

    public String getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(String internetUsers) {
        this.internetUsers = internetUsers;
    }
}
