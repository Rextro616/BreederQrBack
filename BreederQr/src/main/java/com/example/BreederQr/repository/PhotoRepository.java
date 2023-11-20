package com.example.BreederQr.repository;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.models.photo.Photo;
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
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO photo (photo, id_animal, " +
            "created_at, created_by) " +
            "VALUES (?1, ?2, ?3, ?4)",
            nativeQuery = true)
    void insert(
            @Param("photo") String photo,
            @Param("id_animal") Integer idAnimal,
            @Param("created_at") LocalDateTime createdAt,
            @Param("created_by") Integer createdBy);

    @Transactional
    @Query(value = "SELECT id, photo, id_animal, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FROM photo " +
            "WHERE id_animal = ?1 AND deleted is null", nativeQuery = true)
    Optional<List<Photo>> getPhoto(
            @Param("id_animal")Integer idAnimal
    );

    @Transactional
    @Query(value = "SELECT id, photo, id_animal, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FROM photo " +
            "WHERE id = ?1 AND deleted is null", nativeQuery = true)
    Optional<Photo> getPhotoById(
            @Param("id")Integer id
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE photo SET deleted = :deleted, deleted_at = :deleted_at, deleted_by = :deleted_by " +
            "WHERE id = :id",
            nativeQuery = true)
    void softDeletePhoto(
            @Param("deleted") Boolean deleted,
            @Param("deleted_at") LocalDateTime updatedAt,
            @Param("deleted_by") Integer deletedBy,
            @Param("id") Integer id
    );
}
