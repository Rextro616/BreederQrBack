package com.example.BreederQr.controllers;

import com.example.BreederQr.config.swagger.BreederPlaceWrapper;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.BreedingPlaceRepository;
import com.example.BreederQr.services.BreedingPlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/breedingPlace")
public class BreedingPlaceController {

    BreedingPlaceService breedingPlaceService;

    @PostMapping(value = "/postBreedingPlace", consumes = "multipart/form-data")
    public ResponseEntity<String> saveBreedingPlace(@Valid @ModelAttribute BreederPlaceWrapper breederPlaceWrapper, @RequestParam String token){
        breedingPlaceService.saveBreedingPlace(breederPlaceWrapper, token);
        return new ResponseEntity<>("Criadero creado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/getBreedingPlace")
    public ResponseEntity<?> getBreedingPlace(@RequestParam String token){
        Optional<BreedingPlace> breedingPlace = breedingPlaceService.getBreeder(token);

        if(breedingPlace.isPresent()){
            return new ResponseEntity<BreedingPlace>(breedingPlace.get(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("El criadero no existe", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/putBreedingPlace")
    public ResponseEntity<?> putBreedingPlace(@Valid @ModelAttribute BreederPlaceWrapper breederPlaceWrapper, @RequestParam String token){

    }
}
