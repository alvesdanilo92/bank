package br.com.bank.authenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.bank"})
public class ApiAuthenticatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAuthenticatorApplication.class, args);
    }


}
