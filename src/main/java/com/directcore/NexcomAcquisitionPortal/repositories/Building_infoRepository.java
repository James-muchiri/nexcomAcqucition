package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Building_info;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface Building_infoRepository extends CrudRepository<Building_info, Long> {

    Building_info findById(Integer id);

    @Query("SELECT b FROM Building_info b WHERE b.building_name LIKE %?1% OR b.building_type LIKE %?1%")
    List<Building_info> findByNameLike(String search);

    @Query("SELECT b FROM Building_info b WHERE b.createDateTime >= :creationDateTime")
     List<Building_info> findAllWithCreateDateTimeAfter(LocalDateTime creationDateTime);

    Building_info findTopByOrderByIdDesc();
}
