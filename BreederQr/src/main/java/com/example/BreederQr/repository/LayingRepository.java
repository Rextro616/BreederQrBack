package com.example.BreederQr.repository;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.laying.Laying;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LayingRepository extends JpaRepository<Laying, Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO laying (id_animal, amount, deads, " +
            "created_at, created_by) " +
            "VALUES (?1, ?2, ?3, ?4, ?5)",
            nativeQuery = true)
    void insert(
            @Param("id_animal") Integer idAnimal,
            @Param("amount") Integer amount,
            @Param("deads") Integer deads,
            @Param("created_at") LocalDateTime createdAt,
            @Param("created_by") Integer createdBy);

    @Transactional
    @Query(value = "SELECT id, id_animal, amount, deads, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FROM laying WHERE id_animal = ?1 AND deleted is null " +
            "LIMIT ?2 , ?3", nativeQuery = true)
    Optional<List<Laying>> getAllLayings(
            @Param("id_animal") Integer idAnimal,
            @Param("where") Integer where,
            @Param("from") Integer from
    );

    @Transactional
    @Query(value = "SELECT id, id_animal, amount, deads, " +
            "created_at, created_by, deleted, deleted_at, deleted_by, updated_at, updated_by " +
            "FROM laying WHERE id = ?1 AND deleted is null", nativeQuery = true)
    Optional<Laying> getLayingById(
            @Param("id") Integer id
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE laying SET amount = :amount, deads = :deads, " +
            "updated_at = :updated_at, updated_by = :updated_by " +
            "WHERE id = :id AND deleted is null",
            nativeQuery = true)
    void updateLaying(
            @Param("id") Integer id,
            @Param("amount") Integer amount,
            @Param("deads") Integer deads,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("updated_by") Integer updatedBy
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE laying SET deleted = :deleted, deleted_at = :deleted_at, deleted_by = :deleted_by " +
            "WHERE id = :id",
            nativeQuery = true)
    void softDeleteLaying(
            @Param("deleted") Boolean deleted,
            @Param("deleted_at") LocalDateTime deletedAt,
            @Param("deleted_by") Integer deletedBy,
            @Param("id") Integer id
    );

    @Procedure("get_deads")
    Integer getDeads(Integer id);


    @Procedure("get_total")
    Integer getAmount(Integer id);
}
