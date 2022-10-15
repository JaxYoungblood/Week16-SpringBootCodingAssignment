package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication      //tells SpringBoot JeepSales is configuration class
public class JeepSales {

    public static void main(String[] args) {
        SpringApplication.run(JeepSales.class, args);   //Boots Spring Application
    }//end METHOD main
    
    
}



// To remind me of the address
// http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/