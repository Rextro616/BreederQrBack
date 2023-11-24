package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breedingplace.BreederPlaceWrapper;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.models.photo.Photo;
import com.example.BreederQr.models.photo.PhotoWrapper;
import com.example.BreederQr.services.CommonsService;
import com.example.BreederQr.services.PhotoService;
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
@RequestMapping("/photo")
public class PhotoController {
    PhotoService photoService;
    CommonsService commonsService;

    @PostMapping(value = "/postPhoto", consumes = "multipart/form-data")
    public ResponseEntity<String> saveBreedingPlace(@Valid @ModelAttribute PhotoWrapper photoWrapper, @RequestParam String token) throws IOException {
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        photoService.postPhoto(token, photoWrapper);
        return new ResponseEntity<>("Foto subida exitosamente", HttpStatus.OK);
    }

    @GetMapping("/getPhoto")
    public ResponseEntity<?> getPhoto(@RequestParam Integer idBreedingPace, @RequestParam Integer where, @RequestParam Integer from){
        Optional<List<Photo>> photo = photoService.getPhoto(idBreedingPace, where, from);

        if(photo.isPresent()){
            return new ResponseEntity<>(photo.get(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("El animal no existe", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deletePhoto")
    public ResponseEntity<?> softDeleteBreedingPlace(@RequestParam Integer idPhoto, @RequestParam String token){
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        if (photoService.deletePhoto(idPhoto, token)){
            return new ResponseEntity<>("Imagen borrada", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al borrar la imagen", HttpStatus.BAD_REQUEST);
    }
}
