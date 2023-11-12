package com.example.BreederQr.repository;

import com.example.BreederQr.models.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into breeder (id_specie, id_breeding_place, birthday, description, register_number," +
            "gender, qr, created_at, created_by) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)",
            nativeQuery = true)
    void insert(
            @Param("id_specie") Integer idSpecie,
            @Param("id_breeding_place") Integer idBreedingPlace,
            @Param("birthday") String birthday,
            @Param("description") String description,
            @Param("register_number") String registerNumber,
            @Param("gender") String gender,
            @Param("qr") String qr,
            @Param("created_at") LocalDateTime createdAt,
            @Param("created_by") int createdBy);
}
