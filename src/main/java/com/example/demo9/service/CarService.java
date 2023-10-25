package com.example.demo9.service;

import com.example.demo9.dto.CarDto;
import com.example.demo9.model.CarModel;
import com.example.demo9.repository.CarRepository;
import com.example.demo9.utils.CarMapper;
import com.example.demo9.utils.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarModel> carsList = new ArrayList<>();

    public List<CarModel> getCarsList() {
        return carRepository.findAll();
    }

    public CarModel getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("cc"));
    }


    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public CarDto addCar(CarDto carDto) {
        CarModel carModel = CarMapper.toCarModel(carDto);
        CarModel addCar = carRepository.save(carModel);
        return CarMapper.toCarDto(addCar);
    }

    public List<CarModel> getCarByColor(String color) {
        return carRepository.listCarsByColor(color);
    }

    public CarDto updateCarColor(Long id, CarDto carDto) {
        CarModel carModel = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException("Car not found"));
        carModel.setColor(carDto.getColor());
        carModel = carRepository.save(carModel);
        return CarMapper.toCarDto(carModel);
    }
}
