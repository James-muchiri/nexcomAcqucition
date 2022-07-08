package com.directcore.NexcomAcquisitionPortal.repositories;



import com.directcore.NexcomAcquisitionPortal.model.Building_information;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Building_informationRepository extends CrudRepository<Building_information, Integer> {

}
