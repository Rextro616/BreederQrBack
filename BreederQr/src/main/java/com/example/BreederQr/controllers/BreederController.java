package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.repository.BreederRepository;
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
    BreederRepository breederRepository;

    @PostMapping("postBreeder")
    public ResponseEntity<String> create (@RequestBody @Valid Breeder breeder) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(breeder.getPassword());

        breederRepository.insert(
                breeder.getName(),
                breeder.getLast_name(),
                breeder.getSecond_last_name(),
                breeder.getUsername(),
                password,
                breeder.getMail(),
                LocalDateTime.now(),
                1);

        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/putBreeder")
    public ResponseEntity<Breeder> getBreeder(@RequestParam Integer idBreeder){
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(idBreeder);

        return breeder1.map(breeder -> new ResponseEntity<>(breeder, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/putBreeder")
    public  ResponseEntity<Breeder> updateBreeder(@RequestBody @Valid Breeder breeder) {
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(breeder.getId());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(breeder.getPassword());

        if (breeder1.isPresent()){
            breederRepository.updateBreeder(password, breeder.getId(), LocalDateTime.now());
            return new ResponseEntity<>(breederRepository.searchBreeder(breeder.getId()).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteBreeder")
    public  ResponseEntity<Breeder> updateBreeder(@RequestBody @Valid Breeder breeder) {
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(breeder.getId());

        if (breeder1.isPresent()){
            breederRepository.updateBreeder(password, breeder.getId(), LocalDateTime.now());
            return new ResponseEntity<>(breederRepository.searchBreeder(breeder.getId()).get(), HttpStatus.OK);
        }
    }
}
