package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Cluster;
import com.directcore.NexcomAcquisitionPortal.model.Region;
import com.directcore.NexcomAcquisitionPortal.model.Zone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClusterRepository extends CrudRepository<Cluster, Long> {





    List<Cluster>  findAllByAreaId(Integer id);
}
