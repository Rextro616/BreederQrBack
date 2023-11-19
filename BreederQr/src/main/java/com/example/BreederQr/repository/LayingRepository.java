package com.example.BreederQr.repository;

import com.example.BreederQr.models.laying.Laying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayingRepository extends JpaRepository<Laying, Integer> {
}
