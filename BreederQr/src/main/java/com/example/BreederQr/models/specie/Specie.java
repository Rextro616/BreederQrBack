package com.example.BreederQr.models.specie;

import com.example.BreederQr.models.animal.Animal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Specie  {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "specie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Animal animal;

    //Auditable
    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
    @Column(updatable = false)
    int createdBy;
    @CreatedDate
    @Column
    LocalDateTime updatedAt;
    @Column
    int updatedBy;
    @Column()
    Boolean deleted;
    @Column(updatable = false)
    LocalDateTime deletedAt;
    @Column(updatable = false)
    int deletedBy;
}
