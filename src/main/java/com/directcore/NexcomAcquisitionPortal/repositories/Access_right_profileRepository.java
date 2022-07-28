package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Access_right_profile;
import com.directcore.NexcomAcquisitionPortal.model.Admi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Access_right_profileRepository  extends CrudRepository<Access_right_profile, Long> {


}
