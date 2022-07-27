package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Building_info;
import com.directcore.NexcomAcquisitionPortal.model.Building_profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Building_profileRepository extends CrudRepository<Building_profile, Long> {
    Building_profile findById(Integer id);
}
