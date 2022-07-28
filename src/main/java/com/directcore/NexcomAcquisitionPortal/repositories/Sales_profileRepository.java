package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Sales_profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sales_profileRepository  extends CrudRepository<Sales_profile, Integer> {
   Sales_profile findByBuildingcode(String building_code);
}
