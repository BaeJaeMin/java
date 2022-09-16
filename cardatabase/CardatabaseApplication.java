package com.ezdesign.cardatabase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ezdesign.cardatabase.domain.Car;
import com.ezdesign.cardatabase.domain.CarRepository;
import com.ezdesign.cardatabase.domain.Owner;
import com.ezdesign.cardatabase.domain.OwnerRepository;
import com.ezdesign.cardatabase.domain.Usr;
import com.ezdesign.cardatabase.domain.UserRepository;


@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger =
			LoggerFactory.getLogger(CardatabaseApplication.class);
	
	@Autowired
	private CarRepository repository;
	
	@Autowired
	private OwnerRepository orepository;
	
	@Autowired
	private UserRepository urepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}
	@Override
	public void run(String... args) throws Exception{
		//Add owner objects and save these to db
		Owner owner1 = new Owner("John", "Johnson"); //객체
		Owner owner2 = new Owner("Mary", "Robinson");
		
		orepository.saveAll(Arrays.asList(owner1, owner2));
		
		//Set<Owner> owners1 = new HashSet<Owner>();
		//Set<Owner> owners2 = new HashSet<Owner>();

		
		//owners1.add(owner1);
		//owners2.add(owner2);
		//owners2.add(owner1);
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000, owner2));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000, owner2));
		

		
		for(Car car : repository.findAll()) {
			logger.info(car.getBrand()+ " " + car.getModel());
		}
		urepository.save(new Usr("user", "$2a$10$.5TA0J/YCWDW3OJmQWsbie9vlU8rXo0GN4mWvgUi/zFiT6xvs6pb2", "USR"));
		urepository.save(new Usr("admin", "$2a$10$fwliSl/oEHax.cS0FQn66.v8DtvVimDpLC90c7rP20EIQsA6dS1wG", "ADMIN"));
	}

}
