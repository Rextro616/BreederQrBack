package com.example.BreederQr.repository;

import com.example.BreederQr.models.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO animal (name, id_specie, id_breeding_place, birthday, description, register_number," +
            "gender, qr, created_at, created_by) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)",
            nativeQuery = true)
    void insert(
            @Param("name") String name,
            @Param("id_specie") Integer idSpecie,
            @Param("id_breeding_place") Integer idBreedingPlace,
            @Param("birthday") String birthday,
            @Param("description") String description,
            @Param("register_number") String registerNumber,
            @Param("gender") String gender,
            @Param("qr") String qr,
            @Param("created_at") LocalDateTime createdAt,
            @Param("created_by") Integer createdBy);

    @Transactional
    @Query(value = "SELECT id, name, id_specie, id_breeding_place, birthday, description, register_number, gender, qr, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FROM animal WHERE id_breeding_place = ?1 AND deleted is null " +
            "LIMIT ?2 , ?3", nativeQuery = true)
    Optional<List<Animal>> getAllAnimals(
            @Param("id_breeding_place") Integer idBreedingPlace,
            @Param("where") Integer where,
            @Param("from") Integer from
    );

    @Transactional
    @Query(value = "SELECT id, name, id_specie, id_breeding_place, birthday, description, register_number, gender, qr, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FROM animal WHERE id = ?1 AND deleted is null", nativeQuery = true)
    Optional<Animal> getAnimalById(
            @Param("id") Integer id
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE animal SET name = :name, id_specie = :id_specie, register_number = :register_number, " +
            "birthday = :birthday, description = :description, gender =:gender, qr =:qr," +
            "updated_at = :updated_at, updated_by = :updated_by " +
            "WHERE id = :id AND deleted is null",
            nativeQuery = true)
    void updateAnimal(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("id_specie") Integer idSpecie,
            @Param("birthday") String birthday,
            @Param("description") String description,
            @Param("register_number") String registerNumber,
            @Param("gender") String gender,
            @Param("qr") String qr,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("updated_by") Integer updatedBy
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE animal SET deleted = :deleted, deleted_at = :deleted_at, deleted_by = :deleted_by " +
            "WHERE id = :id",
            nativeQuery = true)
    void softDeleteAnimal(
            @Param("deleted") Boolean deleted,
            @Param("deleted_at") LocalDateTime updatedAt,
            @Param("deleted_by") Integer deletedBy,
            @Param("id") Integer id
    );
}
