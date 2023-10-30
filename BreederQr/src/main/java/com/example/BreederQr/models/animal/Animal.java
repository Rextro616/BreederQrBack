package com.example.BreederQr.models.animal;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.models.specie.Specie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Animal {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_specie", nullable = false)
    Specie specie;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_breeding_place", nullable = false)
    BreedingPlace breedingPlace;

    @Column(nullable = false, length = 20)
    private String birthday;

    @Column(nullable = false, length = 20)
    private String description;

    @Column(nullable = false, length = 20)
    private String register_number;

    //cambiar a enum
    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false, length = 50)
    private String qr;


}
