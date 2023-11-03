package com.example.BreederQr.models.specie;

import com.example.BreederQr.infra.Auditable;
import com.example.BreederQr.models.animal.Animal;
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
public class Specie extends Auditable {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String birthday;

    @JsonIgnore
    @OneToOne(mappedBy = "specie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Animal animal;
}
