package com.directcore.NexcomAcquisitionPortal.repositories;

import com.directcore.NexcomAcquisitionPortal.model.Login_logs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Login_logsRepository extends CrudRepository<Login_logs, Integer> {
    List<Login_logs> findAllByadminid(Integer id);
}
