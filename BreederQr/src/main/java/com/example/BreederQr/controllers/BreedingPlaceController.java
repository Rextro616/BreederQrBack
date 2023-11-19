package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breedingplace.BreederPlaceWrapper;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.services.BreedingPlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        Optional<BreedingPlace> breedingPlace = breedingPlaceService.getBreedingPlace(token);

        if(breedingPlace.isPresent()){
            return new ResponseEntity<BreedingPlace>(breedingPlace.get(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("El criadero no existe", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/putBreedingPlace", consumes = "multipart/form-data")
    public ResponseEntity<String> putBreedingPlace(@Valid @ModelAttribute BreederPlaceWrapper breederPlaceWrapper, @RequestParam String token){
        if (breedingPlaceService.putBreedingPlace(breederPlaceWrapper, token)){
            return new ResponseEntity<>("Criadero actualizado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al actualizar criadero", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/deleteBreedingPlace")
    public ResponseEntity<?> softDeleteBreedingPlace(@RequestParam String token){
        if (breedingPlaceService.deleteBreedingPlace(token)){
            return new ResponseEntity<>("Criadero borrado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al borrar el criadero", HttpStatus.BAD_REQUEST);
    }
}
