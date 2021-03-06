package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Zone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends CrudRepository<Zone, Integer> {






    List<Zone> findAllByRegionId(Integer id);

    @Query("SELECT z FROM Zone z WHERE z.name LIKE %?1%")
    List<Zone> findByNameLike(String search);


}
