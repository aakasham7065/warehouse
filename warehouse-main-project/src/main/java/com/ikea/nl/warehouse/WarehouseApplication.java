package com.ikea.nl.warehouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ikea.nl.warehouse" })
public class WarehouseApplication {

	public static void main(String[] args) {

		SpringApplication.run(WarehouseApplication.class, args);
	}

}
