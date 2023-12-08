package com.example.BreederQr.models.laying;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
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
public class Laying {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_animal", nullable = false)
    Animal animal;

    @Column(nullable = false)
    private Integer amount;

    @Column()
    private Integer deads;

    //Auditable
    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
    @Column(updatable = false)
    Integer createdBy;
    @CreatedDate
    @Column
    LocalDateTime updatedAt;
    @Column
    Integer updatedBy;
    @Column()
    Boolean deleted;
    @Column(updatable = false)
    LocalDateTime deletedAt;
    @Column(updatable = false)
    Integer deletedBy;
}
