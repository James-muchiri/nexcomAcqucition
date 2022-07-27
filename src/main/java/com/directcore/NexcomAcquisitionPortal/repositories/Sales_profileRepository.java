package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Sale_profile;

import com.directcore.NexcomAcquisitionPortal.model.Sales_profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Sales_profileRepository  extends CrudRepository<Sales_profile, Integer> {
}
