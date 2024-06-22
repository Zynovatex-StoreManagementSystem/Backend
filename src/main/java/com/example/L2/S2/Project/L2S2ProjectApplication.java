package com.example.L2.S2.Project;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.L2.S2.Project.model.Category;
import com.example.L2.S2.Project.model.Product;
import com.example.L2.S2.Project.repository.CategoryRepository;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@SpringBootApplication
public class L2S2ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(L2S2ProjectApplication.class, args);
	}

}
