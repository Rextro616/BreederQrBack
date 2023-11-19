package com.example.BreederQr.controllers;

import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.services.AnimalService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {
    AnimalService animalService;

    @PostMapping(value = "/postAnimal", consumes = "multipart/form-data")
    public ResponseEntity<String> createAnimal (@Valid @ModelAttribute AnimalWrapper animalWrapper, @RequestParam String token) {
        animalService.createAnimal(animalWrapper, token);
        return new ResponseEntity<>("Animal creado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/getAllAnimals")
    public ResponseEntity<?> getAllAnimals(@RequestParam String token){
        Optional<List<Animal>> animals = animalService.getAllAnimals(token);
        if (animals.isPresent() && animals.get().size()>0){
            return new ResponseEntity<>(animals.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No existen animales en el criadero", HttpStatus.NOT_FOUND);
    }


}
