package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.models.breeder.BreederWrapper;
import com.example.BreederQr.repository.BreederRepository;
import com.example.BreederQr.services.BreederService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/breeder")
public class BreederController {
    BreederService breederService;

    @PostMapping("postBreeder")
    public ResponseEntity<String> createBreeder (@Valid @RequestBody BreederWrapper breeder) {
        if (breederService.createBreeder(breeder)){
            return ResponseEntity.ok().body("usuario creado");
        } else {
            return ResponseEntity.internalServerError().body("El email ya existe");
        }
    }

    @GetMapping("/getBreeder")
    public ResponseEntity<Breeder> getBreeder(@RequestParam String token){
        return breederService.getBreeder(token).map(breeder -> {
            breeder.setPassword("");
            return new ResponseEntity<>(breeder, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/putBreeder")
    public  ResponseEntity<?> updateBreeder(@Valid @RequestBody BreederWrapper breeder, @RequestParam String token) {
        Breeder breeder1 = breederService.putBreeder(breeder, token);

        if (breeder1 != null){
            return new ResponseEntity<>("Criador creado exitosamente", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/softDeleteBreeder")
    public  ResponseEntity<Breeder> softDeleteBreeder(@RequestParam String token) {
        Breeder breeder1 = breederService.softDeleteBreeder(token);

        if (breeder1 != null){
            return new ResponseEntity<>(breeder1, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
