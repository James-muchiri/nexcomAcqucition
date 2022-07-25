package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.Roles_admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles_admin, Long> {

    Roles_admin findByName(String name);

    Roles_admin findAllById(Integer id);

    Roles_admin  findById(Integer id);


}

