package com.example.BreederQr.controllers;

import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.repository.BreederRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@Controller
@AllArgsConstructor
public class BreederController {

    BreederRepository breederRepository;


    @PostMapping("/breeder/create")
    public ResponseEntity<Breeder> create (@RequestBody @Valid Breeder breeder) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(breeder.getPassword());

        Optional<Breeder>  breeder1 = breederRepository.insert(
                breeder.getName(),
                breeder.getLast_name(),
                breeder.getSecond_last_name(),
                breeder.getUsername(),
                password,
                breeder.getMail(),
                LocalDateTime.now(),
                1);

        return ResponseEntity.ok().body(breeder1.get());


    }
}
