package com.example.L2.S2.Project.controller;

import com.example.L2.S2.Project.dao.request.DriverRequest;
import com.example.L2.S2.Project.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/addDriver")
    // @PreAuthorize("hasAnyAuthority('Admin')")
    public String registerDriver(@RequestBody DriverRequest request) {
        return driverService.addDriver(request);
    }

    // public ResponseEntity<String> addDriver(@RequestBody DriverRequest request){
    // driverService.addDriver(request);
    // return ResponseEntity.ok("Driver added successfully");
    // }

}
