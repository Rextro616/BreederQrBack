package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.repository.BreederRepository;
import com.example.BreederQr.services.BreederService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createBreeder (@RequestBody @Valid Breeder breeder) {
        if (breederService.createBreeder(breeder)){
            return ResponseEntity.ok().body("usuario creado");
        } else {
            return ResponseEntity.internalServerError().body("El email ya existe");
        }
    }

    @GetMapping("/getBreeder")
    public ResponseEntity<Breeder> getBreeder(@RequestParam Integer idBreeder){
        return breederService.getBreeder(idBreeder).map(breeder -> {
            breeder.setPassword("");
            return new ResponseEntity<>(breeder, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/putBreeder")
    public  ResponseEntity<Breeder> updateBreeder(@RequestBody @Valid Breeder breeder) {
        Breeder breeder1 = breederService.putBreeder(breeder);

        if (breeder1 != null){
            return new ResponseEntity<>(breeder, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/softDeleteBreeder")
    public  ResponseEntity<Breeder> softDeleteBreeder(@RequestBody @Valid Breeder breeder) {
        Breeder breeder1 = breederService.softDeleteBreeder(breeder);

        if (breeder1 != null){
            return new ResponseEntity<>(breeder1, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
