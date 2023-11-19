package com.example.BreederQr.models.animal;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.models.laying.Laying;
import com.example.BreederQr.models.photo.Photo;
import com.example.BreederQr.models.specie.Specie;
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
public class Animal{
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_specie", nullable = false)
    Specie specie;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_breeding_place", nullable = false)
    BreedingPlace breedingPlace;

    @Column(length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String birthday;

    @Column(nullable = false, length = 20)
    private String description;

    @Column(nullable = false, length = 20)
    private String register_number;

    //cambiar a enum
    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false, length = 200)
    private String qr;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Laying> layings;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Photo> photos;

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
