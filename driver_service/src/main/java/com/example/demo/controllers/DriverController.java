package com.example.demo.controllers;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.ResponseObject;
import com.example.demo.models.Driver;
import com.example.demo.models.Vehicle;
import com.example.demo.services.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/api/v1/Drivers")
public class DriverController {
    
    @Autowired
    private DriverService driverService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllDriver() {
        List<Driver> foundDrivers = driverService.findAll();

        for (Driver driver : foundDrivers) {
            List<Vehicle> ownVehicles = driverService.getOwnVehicles(driver.getId());
            driver.setOwnVehicles(ownVehicles);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Get all drivers successfully", driverService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDriverById(@PathVariable Long id) {
        Optional<Driver> driverOptional = driverService.findById(id);
        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            List<Vehicle> ownVehicles = driverService.getOwnVehicles(id);
            driver.setOwnVehicles(ownVehicles);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get driver with id = " + id + " successfully", driver)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("failed", "Cannot find driver with id = " + id, null)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createNewDriver(@RequestBody Driver newDriver) {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Create new driver successfully", driverService.create(newDriver))
        );
    }
}
