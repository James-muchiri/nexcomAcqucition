package com.directcore.NexcomAcquisitionPortal.repositories;


import com.directcore.NexcomAcquisitionPortal.model.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer> {

}
