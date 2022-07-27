package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.*;


@Entity
public class Sales_profile {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    // sales profile
    @Column(name = "possible_sales")
    private String       possible_sales;
    @Column(name = "exsistingProvider")
    private String        exsistingProvider;
    @Column(name = "withinternet")
    private String           withinternet;
    @Column(name = "leadingprovider")
    private String        leadingprovider;
    @Column(name = "lowestrent")
    private String       lowestrent;
    @Column(name = "mediumrent")
    private String         mediumrent;
    @Column(name = "highestrent")
    private String        highestrent;
    @Column(name = "incomeclass")
    private String     incomeclass;
    @Column(name = "targetplan")
    private String     targetplan;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPossible_sales() {
        return possible_sales;
    }

    public void setPossible_sales(String possible_sales) {
        this.possible_sales = possible_sales;
    }

    public String getExsistingProvider() {
        return exsistingProvider;
    }

    public void setExsistingProvider(String exsistingProvider) {
        this.exsistingProvider = exsistingProvider;
    }

    public String getWithinternet() {
        return withinternet;
    }

    public void setWithinternet(String withinternet) {
        this.withinternet = withinternet;
    }

    public String getLeadingprovider() {
        return leadingprovider;
    }

    public void setLeadingprovider(String leadingprovider) {
        this.leadingprovider = leadingprovider;
    }

    public String getLowestrent() {
        return lowestrent;
    }

    public void setLowestrent(String lowestrent) {
        this.lowestrent = lowestrent;
    }

    public String getMediumrent() {
        return mediumrent;
    }

    public void setMediumrent(String mediumrent) {
        this.mediumrent = mediumrent;
    }

    public String getHighestrent() {
        return highestrent;
    }

    public void setHighestrent(String highestrent) {
        this.highestrent = highestrent;
    }

    public String getIncomeclass() {
        return incomeclass;
    }

    public void setIncomeclass(String incomeclass) {
        this.incomeclass = incomeclass;
    }

    public String getTargetplan() {
        return targetplan;
    }

    public void setTargetplan(String targetplan) {
        this.targetplan = targetplan;
    }
}
