package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.Column;

public class Building_form {


            // location
            @Column(name = "building_cluster")
            private Integer   building_cluster;

            // building profile
            @Column(name = "building_name")
            private String building_name;
            @Column(name = "acquisitionPurpose")
            private String  acquisitionPurpose;
            @Column(name = "use_type")
            private String use_type;
            @Column(name = "building_type" )
            private String     building_type;
            @Column(name = "building_state")

            private String     building_state;
            @Column(name = "power")

            private String      power;
            @Column(name = "backup")

            private String backup;
            @Column(name = "backup_text")
            private String  backup_text;

            @Column(name = "numberofUnits")
            private String    numberofUnits;
            @Column(name = "blocks")

            private String     blocks;
            @Column(name = "floors")
            private String   floors;
            @Column(name = "security")
            private String       security;
            @Column(name = "street_name")
            private String     street_name;
            @Column(name = "building_description")
            private String      building_description;
            @Column(name = "pp_photo")
            private String        pp_photo;


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

    public Integer getBuilding_cluster() {
        return building_cluster;
    }

    public void setBuilding_cluster(Integer building_cluster) {
        this.building_cluster = building_cluster;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getAcquisitionPurpose() {
        return acquisitionPurpose;
    }

    public void setAcquisitionPurpose(String acquisitionPurpose) {
        this.acquisitionPurpose = acquisitionPurpose;
    }

    public String getUse_type() {
        return use_type;
    }

    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }

    public String getBuilding_type() {
        return building_type;
    }

    public void setBuilding_type(String building_type) {
        this.building_type = building_type;
    }

    public String getBuilding_state() {
        return building_state;
    }

    public void setBuilding_state(String building_state) {
        this.building_state = building_state;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public String getBackup_text() {
        return backup_text;
    }

    public void setBackup_text(String backup_text) {
        this.backup_text = backup_text;
    }

    public String getNumberofUnits() {
        return numberofUnits;
    }

    public void setNumberofUnits(String numberofUnits) {
        this.numberofUnits = numberofUnits;
    }

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

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getBuilding_description() {
        return building_description;
    }

    public void setBuilding_description(String building_description) {
        this.building_description = building_description;
    }

    public String getPp_photo() {
        return pp_photo;
    }

    public void setPp_photo(String pp_photo) {
        this.pp_photo = pp_photo;
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
