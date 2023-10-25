package com.example.demo9.controller;

import com.example.demo9.dto.CarDto;
import com.example.demo9.model.CarModel;
import com.example.demo9.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCars() {
        try {
            List<CarModel> carsList = carService.getCarsList();
            return ResponseEntity.ok(carsList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
        try {
            CarModel car = carService.getCarById(id);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        try {
            CarDto addedCarDto = carService.addCar(carDto);
            return ResponseEntity.ok(addedCarDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<List<CarModel>> deleteCar(@PathVariable("id") Long id) {
        try {
            carService.deleteCarById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<CarModel>> getCarByColor(@PathVariable String color) {
        try {
            List<CarModel> carByColor = carService.getCarByColor(color);
            return ResponseEntity.ok(carByColor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/color")
    public ResponseEntity<CarDto> updateCarColor(@PathVariable Long id,
                                                 @RequestBody CarDto carDto) {
        try {
            CarDto updateCarColor = carService.updateCarColor(id, carDto);
            return ResponseEntity.ok(updateCarColor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
