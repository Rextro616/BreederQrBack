package com.example.BreederQr.repository;

import com.example.BreederQr.models.breeder.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface BreederRepository extends JpaRepository<Breeder, Integer> {
    @Query(nativeQuery = true,
            value = "select * from breeder where mail = :mail")
    Optional<Breeder> findByMail(@Param("mail") String mail);

    @Transactional
    @Modifying
    @Query(value = "insert into breeder (name, last_name,second_last_name,username,password,mail,created_at,created_by) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)",
            nativeQuery = true)
    void insert(
            @Param("name") String name,
            @Param("last_name") String last_name,
            @Param("second_last_name") String second_last_name,
            @Param("username") String username,
            @Param("password") String password,
            @Param("mail") String mail,
            @Param("created_at") LocalDateTime createdAt

    );
    @Transactional
    @Query(value = "SELECT id, name, last_name, second_last_name, username, " +
            "password, mail, created_at, created_by, deleted, deleted_at, " +
            "deleted_by, updated_at, updated_by FROM breeder where id = ?1 AND deleted is null",
            nativeQuery = true)
    Optional<Breeder> searchBreeder(
            @Param("id") Integer id
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE breeder SET password = :password, updated_at = :updated_at WHERE id = :id",
            nativeQuery = true)
    void updateBreeder(
            @Param("password") String password,
            @Param("id") Integer id,
            @Param("updated_at") LocalDateTime updated_at
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE breeder SET deleted = :deleted, deleted_at = :deleted_at WHERE id = :id",
            nativeQuery = true)
    void softDeleteBreeder(
            @Param("deleted") Boolean deleted,
            @Param("deleted_at") LocalDateTime updated_at,
            @Param("id") Integer id
    );
}
