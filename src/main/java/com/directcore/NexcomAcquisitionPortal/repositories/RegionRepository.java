package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RegionRepository extends CrudRepository<Region, Integer> {

    @Query("SELECT r FROM Region r WHERE r.name LIKE %?1%")
    List<Region> findByNameLike(String search);
}
