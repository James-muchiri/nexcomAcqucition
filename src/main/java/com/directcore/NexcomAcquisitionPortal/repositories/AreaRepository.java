package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Area;
import com.directcore.NexcomAcquisitionPortal.model.Region;
import com.directcore.NexcomAcquisitionPortal.model.Zone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<Area, Long> {





    Object findById(Integer id);

    List<Area> findAllByZoneId(Integer id);
}
