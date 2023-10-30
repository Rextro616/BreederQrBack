package com.example.BreederQr.repository;

import com.example.BreederQr.models.breeder.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreederRepository extends JpaRepository<Breeder, Integer> {

}
