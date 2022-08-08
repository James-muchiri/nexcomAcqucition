package com.directcore.NexcomAcquisitionPortal;

import com.directcore.NexcomAcquisitionPortal.model.Admi;
import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.time.DayOfWeek;
import java.util.List;


@SpringBootApplication(scanBasePackages = {"com.directcore"}, //
		exclude = { //
				HibernateJpaAutoConfiguration.class, //
				DataSourceAutoConfiguration.class, //
				SecurityAutoConfiguration.class, //
				DataSourceTransactionManagerAutoConfiguration.class//
		})

@EnableAutoConfiguration
@ServletComponentScan
@EnableJdbcHttpSession
public class NexcomAcquisitionPortalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {


		SpringApplication.run(NexcomAcquisitionPortalApplication.class, args);




	}



}
