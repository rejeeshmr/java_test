package com.example.demo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
}

@Component
class BookingCommandLineRunner implements CommandLineRunner{

	@Autowired BookingRepository repo; 
	
	@Override
	public void run(String... arg0) throws Exception {
		for(Booking b: repo.findAll()){
			System.out.println(b.toString());
		}
		
	}
	
}

@RestController
class BookingController{
	
	@Autowired BookingRepository repo;
	
	@RequestMapping("/bookings")
	Collection<Booking> bookings(){
		return repo.findAll();
	}
}

interface BookingRepository extends JpaRepository<Booking, Long>{
	
	Collection<Booking> findByBookingName(String bookingName);
}

@Entity
class Booking{
	
	@Override
	public String toString() {
		return "Booking [bookingName=" + bookingName + "]";
	}

	@Id @GeneratedValue
	Long id;
	
	String bookingName;

	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}
	
	public Booking(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingName() {
		return bookingName;
	}

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
	
}
