package com.directcore.NexcomAcquisitionPortal.seeders;

import com.directcore.NexcomAcquisitionPortal.model.Admi;
import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import com.directcore.NexcomAcquisitionPortal.validation.UpdatableBCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    @Autowired
    private AdmiRepository admiRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();


    }




    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
    private void seedUsersTable() {

        Admi u = admiRepository.findByEmail("system@admin.com");
        if(u == null) {
            Admi user = new Admi();
            user.setName("System Admin");
            user.setPhone("0712345678");
            user.setEmail("system@admin.com");
            user.setPassword(bcrypt.hash("userForm.getPassword()"));
			user.setIsadmin(1);
            admiRepository.save(user);
            logger.info("Users Seeded");
        } else {
            logger.info("Users Seeding Not Required");
        }
    }

}
