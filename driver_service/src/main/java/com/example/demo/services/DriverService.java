package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Driver;
import com.example.demo.models.ResponseObject;
import com.example.demo.models.Vehicle;
import com.example.demo.repositories.DriverRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver create(Driver newDriver) {
        return driverRepository.save(newDriver);
    }

    @Autowired
    private RestTemplate restTemplate;
    @SuppressWarnings({ "null", "unchecked" })
    public List<Vehicle> getOwnVehicles(Long driverId) {
        ResponseObject responseObject = restTemplate.getForObject("http://localhost:8080/api/v1/Vehicles/drivers/{driverId}", ResponseObject.class, driverId);
        return (List<Vehicle>) responseObject.getData();
    }
}
