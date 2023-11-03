package com.example.BreederQr.repository;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedingPlaceRepository extends JpaRepository<BreedingPlace, Integer> {
    @Query(value = "INSERT INTO breeding_place (`address`, `description`, `logo`, `name`, `register_number`, `id_breeder`) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    Boolean saveBreedingPlaceRepo(String adrees, String description, String logo, String name, String registerNumber, int idBreeder);
}

