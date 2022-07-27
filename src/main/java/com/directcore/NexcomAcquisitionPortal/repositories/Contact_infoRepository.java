package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Contact_info;
import com.directcore.NexcomAcquisitionPortal.model.Contact_profile;
import com.directcore.NexcomAcquisitionPortal.model.Images_info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Contact_infoRepository extends CrudRepository<Contact_profile, Long> {




      Contact_profile findById(Integer id);

    List<Contact_profile> findByBuildingcode(String building_code);
}
