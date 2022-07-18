package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.*;

@Entity
public class Sales_profile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

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
    @Column(name = "buildingId")
    private Integer buildingId;
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

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(String internetUsers) {
        this.internetUsers = internetUsers;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public String getExsistingProviders() {
        return exsistingProviders;
    }

    public void setExsistingProviders(String exsistingProviders) {
        this.exsistingProviders = exsistingProviders;
    }
}
