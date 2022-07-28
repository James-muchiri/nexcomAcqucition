package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Building_profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface Building_profileRepository extends CrudRepository<Building_profile, Long> {
    Building_profile findById(Integer id);

    Building_profile findTopByOrderByIdDesc();



    @Query("SELECT b FROM Building_profile b WHERE b.building_name LIKE %?1% OR b.building_type LIKE %?1%")
    List<Building_profile> findByNameLike(String search);

    @Query("SELECT b FROM Building_profile b WHERE b.createDateTime >= :creationDateTime")
    List<Building_profile> findAllWithCreateDateTimeAfter(LocalDateTime creationDateTime);


    List<Building_profile> findByAdminid(Integer search);
}

