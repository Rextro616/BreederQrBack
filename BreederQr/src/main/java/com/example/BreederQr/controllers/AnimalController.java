package com.example.BreederQr.controllers;

import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.repository.BreedingPlaceRepository;
import com.example.BreederQr.services.AnimalService;
import com.example.BreederQr.services.CommonsService;
import com.google.zxing.WriterException;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {
    AnimalService animalService;
    CommonsService commonsService;

    @PostMapping("/postAnimal")
    public ResponseEntity<String> createAnimal (@Valid @RequestBody AnimalWrapper animalWrapper, @RequestParam String token) throws IOException, WriterException {
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        animalService.createAnimal(animalWrapper, token);
        return new ResponseEntity<>("Animal creado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/getAllAnimals")
    public ResponseEntity<?> getAllAnimals(@RequestParam String token, @RequestParam Integer where, @RequestParam Integer from){
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        Optional<List<Animal>> animals = animalService.getAllAnimals(token, where, from);

        if (animals.isPresent()){
            return new ResponseEntity<>(animals.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No existen animales en el criadero", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAnimalById")
    public ResponseEntity<?> getAnimalById(@RequestParam Integer id){
        Optional<Animal> animal = animalService.getAnimalById(id);

        if (animal.isPresent()){
            return new ResponseEntity<>(animal.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("El animal no existe", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/putAnimal", consumes = "multipart/form-data")
    public ResponseEntity<String> putAnimal(@Valid @RequestBody AnimalWrapper animalWrapper, @RequestParam String token, @RequestParam Integer id) throws IOException, WriterException {
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        if (animalService.putAnimal(animalWrapper, token, id)){
            return new ResponseEntity<>("Animal actualizado correctamente", HttpStatus.OK);
        }

        return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deleteAnimal")
    public ResponseEntity<?> softDeleteAnimal(@RequestParam String token, @RequestParam Integer id){
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        if (animalService.deleteAnimal(token, id)){
            return new ResponseEntity<>("Animal borrado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al borrar al animal", HttpStatus.BAD_REQUEST);
    }
}
