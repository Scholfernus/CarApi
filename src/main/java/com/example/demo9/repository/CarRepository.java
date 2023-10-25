package com.example.demo9.repository;

import com.example.demo9.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findByColor(String color);
    @Query("SELECT c FROM CarModel c WHERE c.color = :color")
    List<CarModel> listCarsByColor(@Param("color") String color);
}
