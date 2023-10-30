package com.example.BreederQr.models.breeder;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Breeder {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String last_name;

    @Column(nullable = false, length = 20)
    private String second_last_name;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 30)
    private String mail;

    @JsonIgnore
    @OneToOne(mappedBy = "breeder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BreedingPlace breedingPlace;
}
