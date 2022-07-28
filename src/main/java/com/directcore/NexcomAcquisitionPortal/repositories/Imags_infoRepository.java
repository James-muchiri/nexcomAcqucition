package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Images_info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Imags_infoRepository extends CrudRepository<Images_info, Long> {


    List <Images_info> findByBuildingId(Integer id);


    Images_info findById(Integer id);
}
