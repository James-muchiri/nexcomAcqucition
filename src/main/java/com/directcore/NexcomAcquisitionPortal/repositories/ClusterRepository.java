package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Cluster;
import com.directcore.NexcomAcquisitionPortal.model.Region;
import org.springframework.data.repository.CrudRepository;

public interface ClusterRepository extends CrudRepository<Cluster, Long> {
}
