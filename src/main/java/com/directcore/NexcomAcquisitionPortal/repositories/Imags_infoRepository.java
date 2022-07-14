package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Contact_info;
import com.directcore.NexcomAcquisitionPortal.model.Images_info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Imags_infoRepository extends CrudRepository<Images_info, Long> {


    Images_info findByBuildingId(Integer id);
}
