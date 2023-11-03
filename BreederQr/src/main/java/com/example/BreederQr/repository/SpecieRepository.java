package com.example.BreederQr.repository;

import com.example.BreederQr.models.specie.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Integer> {
}
