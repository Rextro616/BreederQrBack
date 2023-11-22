package com.example.BreederQr.controllers;

import com.example.BreederQr.models.laying.Laying;
import com.example.BreederQr.models.laying.LayingWrapper;
import com.example.BreederQr.services.CommonsService;
import com.example.BreederQr.services.LayingService;
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
@RequestMapping("/laying")
public class LayingController {

    LayingService layingService;
    CommonsService commonsService;

    @PostMapping("/postLaying")
    public ResponseEntity<String> createLaying (@Valid @RequestBody LayingWrapper layingWrapper, @RequestParam String token) {
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        layingService.createLaying(layingWrapper, token);
        return new ResponseEntity<>("Puesta registrada exitosamente", HttpStatus.OK);
    }

    @GetMapping("/getAllLayings")
    public ResponseEntity<?> getAllLayings(@RequestParam Integer idAnimal, @RequestParam Integer where, @RequestParam Integer from){
        Optional<List<Laying>> layings = layingService.getAllLayings(idAnimal, where, from);

        if (layings.isPresent()){
            return new ResponseEntity<>(layings.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Este animal no ha tenido puestas", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getLayingById")
    public ResponseEntity<?> getLayingById(@RequestParam Integer id){
        Optional<Laying> laying = layingService.getLayingById(id);

        if (laying.isPresent()){
            return new ResponseEntity<>(laying.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("La puesta no existe", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/putLaying")
    public ResponseEntity<String> putLaying(@Valid @RequestBody LayingWrapper layingWrapper, @RequestParam String token, @RequestParam Integer id){
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        if (layingService.putLaying(layingWrapper, token, id)){
            return new ResponseEntity<>("Puesta actualizada correctamente", HttpStatus.OK);
        }

        return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deleteLaying")
    public ResponseEntity<?> softDeleteLaying(@RequestParam String token, @RequestParam Integer id){
        if(commonsService.getIdByToken(token) == null){
            return new ResponseEntity<String>("No autorizado", HttpStatus.FORBIDDEN);
        }

        if (layingService.deleteLaying(token, id)){
            return new ResponseEntity<>("Animal borrado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al borrar al animal", HttpStatus.BAD_REQUEST);
    }
}
