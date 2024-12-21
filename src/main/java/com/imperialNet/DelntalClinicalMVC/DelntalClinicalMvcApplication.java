package com.imperialNet.DelntalClinicalMVC;

import com.imperialNet.DelntalClinicalMVC.dao.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelntalClinicalMvcApplication {

	public static void main(String[] args) {
		DB.createTables();
		SpringApplication.run(DelntalClinicalMvcApplication.class, args);
	}

}
