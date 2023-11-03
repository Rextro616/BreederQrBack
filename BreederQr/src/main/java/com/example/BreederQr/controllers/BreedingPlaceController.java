package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.BreedingPlaceRepository;
import com.example.BreederQr.services.BreedingPlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/breedingPlace")
public class BreedingPlaceController {

    BreedingPlaceRepository breedingPlaceRepository;

    @PostMapping("/postBreedingPlace")
    public ResponseEntity<String> saveBreedingPlace(@Valid @RequestBody BreedingPlace breedingPlace, @RequestParam("logo") MultipartFile multipartFile){
        ResponseEntity<String> answer = uploadLogo(multipartFile);
        //corregir
        int idBreeder = 1;

        if (answer.getStatusCode() == HttpStatus.OK){
            breedingPlaceRepository.saveBreedingPlaceRepo(
                    breedingPlace.getAddress(),
                    breedingPlace.getDescription(),
                    answer.getBody(),
                    breedingPlace.getName(),
                    breedingPlace.getRegister_number(),
                    idBreeder);

            return new ResponseEntity<>("Criadero creado exitosamente", HttpStatus.OK);
        }
        return new ResponseEntity<>(answer.getBody(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> uploadLogo(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        String path = "C:\\Users\\6QW95LA_2004\\IdeaProjects\\BreederQrBack\\BreederQr\\src\\main\\resources\\files\\" + fileName;
        try {
            multipartFile.transferTo( new File(path));
        } catch (Exception e) {
            return new ResponseEntity<>("Files cannot be updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(path);
    }
}
