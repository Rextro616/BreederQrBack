package com.example.BreederQr.repository;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BreedingPlaceRepository extends JpaRepository<BreedingPlace, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO breeding_place (address, description, logo, name, register_number, id_breeder, created_at, created_by) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void saveBreedingPlaceRepo(
            @Param("adress")String adrees,
            @Param("description")String description,
            @Param("logo")String logo,
            @Param("name")String name,
            @Param("register_number")String registerNumber,
            @Param("id_breeder")Integer idBreeder,
            @Param("created_at") LocalDateTime createdAt,
            @Param("created_by") Integer createdBy
    );

    @Transactional
    @Query(value = "SELECT address, description, logo, name, register_number, id_breeder, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FORM breeding_place " +
            "WHERE id_breeder = ?1 AND deleted is null", nativeQuery = true)
    Optional<BreedingPlace> getBreedingPlaceRepo(
            @Param("id_breeder")Integer idBreeder
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE breeding_place SET adress = :adress, description = :description, logo = :logo, " +
            "name = :name, register_number = :register_number, updated_at = :updated_at, updated_by = updated_by" +
            "WHERE id = :id AND deleted is null",
            nativeQuery = true)
    void updateBreedingPlace(
            @Param("id")Integer id,
            @Param("adress")String adrees,
            @Param("description")String description,
            @Param("logo")String logo,
            @Param("name")String name,
            @Param("register_number")String registerNumber,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("updated_by") Integer updatedBy
    );

/*    @Transactional
    @Modifying
    @Query(value = "UPDATE breeder SET deleted = :deleted, deleted_at = :deleted_at WHERE id = :id",
            nativeQuery = true)
    void softDeleteBreeder(
            @Param("deleted") Boolean deleted,
            @Param("deleted_at") LocalDateTime updated_at,
            @Param("id") Integer id
    );*/
}

