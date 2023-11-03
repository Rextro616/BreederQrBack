package com.example.BreederQr.repository;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedingPlaceRepository extends JpaRepository<BreedingPlace, Integer> {
    @Query(value = "INSERT INTO breeding_place (address, description, logo, name, register_number, id_breeder) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    Optional<BreedingPlace> saveBreedingPlaceRepo(
            @Param("adress")String adrees,
            @Param("description")String description,
            @Param("logo")String logo,
            @Param("name")String name,
            @Param("register_number")String registerNumber,
            @Param("id_breeder")Integer idBreeder);
}

