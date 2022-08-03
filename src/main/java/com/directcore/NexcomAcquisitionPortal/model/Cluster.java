package com.directcore.NexcomAcquisitionPortal.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Cluster {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "clustertype")
    private String clustertype;
    @Column(name = "regionId")
    private Integer regionId;

    @Column(name = "zoneId")
    private Integer zoneId;

    @Column(name = "fileNames")
    private List<String> fileNames;


    @Column(name = "areaId")
    private Integer areaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Integer getRegion_id() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }


    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getClustertype() {
        return clustertype;
    }

    public void setClustertype(String clustertype) {
        this.clustertype = clustertype;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public List<String>getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
