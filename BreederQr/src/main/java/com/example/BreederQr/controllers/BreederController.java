package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.repository.BreederRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.query.Param;
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


}
