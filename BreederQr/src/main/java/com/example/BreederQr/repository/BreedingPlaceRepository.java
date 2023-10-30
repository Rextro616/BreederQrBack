package com.example.BreederQr.repository;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedingPlaceRepository extends JpaRepository<BreedingPlace, Integer> {
}
