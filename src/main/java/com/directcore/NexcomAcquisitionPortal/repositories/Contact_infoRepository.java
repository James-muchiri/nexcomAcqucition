package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Contact_info;
import com.directcore.NexcomAcquisitionPortal.model.Images_info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Contact_infoRepository extends CrudRepository<Contact_info, Long> {



    List<Contact_info> findByBuildingId(Integer id);
      Contact_info findById(Integer id);
}
