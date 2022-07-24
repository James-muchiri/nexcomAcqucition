package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Cluster;
import com.directcore.NexcomAcquisitionPortal.model.Region;
import com.directcore.NexcomAcquisitionPortal.model.Zone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClusterRepository extends CrudRepository<Cluster, Long> {



    Object findById(Integer id);

    List<Cluster>  findAllByAreaId(Integer id);

    @Query("SELECT c FROM Cluster c WHERE c.name LIKE %?1%")
    List<Cluster> findByNameLike(String search);
}
