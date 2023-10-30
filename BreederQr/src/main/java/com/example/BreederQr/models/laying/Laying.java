package com.example.BreederQr.models.laying;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
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
public class Laying {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_animal", nullable = false)
    Animal animal;

    @Column(nullable = false)
    private int amount;
}
