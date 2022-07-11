package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Zone;
import org.springframework.data.repository.CrudRepository;

public interface ZoneRepository extends CrudRepository<Zone, Long> {






    Object findAllByRegionId(String idd);
}
