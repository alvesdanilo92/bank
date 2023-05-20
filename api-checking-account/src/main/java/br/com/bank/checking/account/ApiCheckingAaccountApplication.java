package br.com.bank.checking.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.bank"})
public class ApiCheckingAaccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiCheckingAaccountApplication.class, args);
    }
}