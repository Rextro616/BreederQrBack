package com.example.BreederQr.repository;

import com.example.BreederQr.models.breeder.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)",
            nativeQuery = true)
    void insert(
            @Param("name") String name,
            @Param("last_name") String last_name,
            @Param("second_last_name") String second_last_name,
            @Param("username") String username,
            @Param("password") String password,
            @Param("mail") String mail,
            @Param("created_at") LocalDateTime createdAt,
            @Param("created_by") Integer createdBy
    );

}
