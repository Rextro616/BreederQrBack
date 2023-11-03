package com.example.BreederQr.models.breedingplace;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breeder.Breeder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BreedingPlace {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_breeder", nullable = false)
    private Breeder breeder;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String address;

    @Column(nullable = false, length = 20)
    private String register_number;

    @Column(nullable = false, length = 20)
    private String description;

    @Column(nullable = false, length = 20)
    private String logo;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "breedingPlace", cascade = CascadeType.ALL)
    private List<Animal> animals;

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
