package com.example.BreederQr.controllers;

import com.example.BreederQr.config.swagger.BreederPlaceWrapper;
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
import java.io.FileOutputStream;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/breedingPlace")
public class BreedingPlaceController {

    BreedingPlaceRepository breedingPlaceRepository;

    @PostMapping(value = "/postBreedingPlace", consumes = "multipart/form-data")
    public ResponseEntity<String> saveBreedingPlace(@Valid @ModelAttribute BreederPlaceWrapper breederPlaceWrapper){
        String path = uploadLogo(breederPlaceWrapper.getImage());

            breedingPlaceRepository.saveBreedingPlaceRepo(
                    breederPlaceWrapper.getAddress(),
                    breederPlaceWrapper.getDescription(),
                    path,
                    breederPlaceWrapper.getName(),
                    breederPlaceWrapper.getRegister_number(),
                    breederPlaceWrapper.getId_breeder());

            return new ResponseEntity<>("Criadero creado exitosamente", HttpStatus.OK);

    }


    public static String uploadLogo(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String path = "";
        try {
            path = "C:\\Users\\6QW95LA_2004\\IdeaProjects\\BreederQrBack\\BreederQr\\src\\main\\resources\\files\\" + fileName;
            File newFile = new File(path);
            newFile.createNewFile();
            FileOutputStream myfile = new FileOutputStream(newFile);
            myfile.write(file.getBytes());
            myfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
