package com.Gabriel.Biblioteca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Gabriel.Biblioteca.api.services.AutorService;

@SpringBootApplication
public class BibliotecaApiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApiApplication.class, args);
	}
}
