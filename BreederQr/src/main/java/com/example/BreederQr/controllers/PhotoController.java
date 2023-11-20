package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breedingplace.BreederPlaceWrapper;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.models.photo.Photo;
import com.example.BreederQr.models.photo.PhotoWrapper;
import com.example.BreederQr.services.PhotoService;
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
@RequestMapping("/photo")
public class PhotoController {
    PhotoService photoService;

    @PostMapping(value = "/postPhoto", consumes = "multipart/form-data")
    public ResponseEntity<String> saveBreedingPlace(@Valid @ModelAttribute PhotoWrapper photoWrapper, @RequestParam String token){
        photoService.postPhoto(token, photoWrapper);
        return new ResponseEntity<>("Foto subida exitosamente", HttpStatus.OK);
    }

    @GetMapping("/getPhoto")
    public ResponseEntity<?> getPhoto(@RequestParam Integer idBreedingPace){
        Optional<List<Photo>> photo = photoService.getPhoto(idBreedingPace);

        if(photo.isPresent() && !photo.get().isEmpty()){
            return new ResponseEntity<>(photo.get(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("El animal no existe", HttpStatus.NOT_FOUND);
    }
}
