package com.example.BreederQr.controllers;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.repository.AnimalRepository;
import com.example.BreederQr.repository.BreederRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {
    AnimalRepository animalRepository;

    @PostMapping("postBreeder")
    public ResponseEntity<String> create (@RequestBody @Valid Animal animal) {

        animalRepository.insert(
                animal.getSpecie().getId(),
                animal.getBreedingPlace().getId(),
                animal.getBirthday(),
                animal.getDescription(),
                animal.getRegister_number(),
                animal.getGender(),
                animal.getQr(),
                LocalDateTime.now(),
                1);

        return ResponseEntity.ok().body("ok");
    }
}
