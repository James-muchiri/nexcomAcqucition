package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Zone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ZoneRepository extends CrudRepository<Zone, Integer> {






    List<Zone> findAllByRegionId(Integer id);



}
