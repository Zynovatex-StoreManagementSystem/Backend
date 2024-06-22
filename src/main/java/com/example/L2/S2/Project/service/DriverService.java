package com.example.L2.S2.Project.service;

import com.example.L2.S2.Project.dao.request.DriverRequest;
import com.example.L2.S2.Project.model.Driver;
import com.example.L2.S2.Project.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    // public DriverService(DriverRepository driverRepository) {
    // this.driverRepository = driverRepository;
    // }

    public String addDriver(DriverRequest request) {

        Optional<Driver> existingDriver = driverRepository.findByEmail(request.getEmail());
        if (existingDriver.isPresent()) {
            // throw new RuntimeException("Driver already exists with email: " +
            // request.getEmail());
            return "This email is already used.";
        } else {
            Driver driver = new Driver();
            driver.setName(request.getName());
            driver.setEmail(request.getEmail());
            driver.setPhone(request.getPhone());
            driverRepository.save(driver); // Explicit type declaration here
            return "Driver added successfully.";

        }
    }

}
