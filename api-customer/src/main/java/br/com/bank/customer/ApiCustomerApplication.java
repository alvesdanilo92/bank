package br.com.bank.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.bank"})
public class ApiCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCustomerApplication.class, args);
	}

}
