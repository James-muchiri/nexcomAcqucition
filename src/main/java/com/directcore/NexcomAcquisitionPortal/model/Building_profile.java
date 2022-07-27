package com.directcore.NexcomAcquisitionPortal.model;

import javax.persistence.*;


@Entity
public class Building_profile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
