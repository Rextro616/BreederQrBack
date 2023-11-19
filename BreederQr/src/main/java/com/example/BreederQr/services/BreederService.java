package com.example.BreederQr.services;

import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.models.breeder.BreederWrapper;
import com.example.BreederQr.repository.BreederRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@AllArgsConstructor
@Service
public class BreederService {
    BreederRepository breederRepository;
    CommonsService commonsService;

    public Boolean createBreeder(BreederWrapper breeder){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(breeder.getPassword());

        if (breederRepository.findByMail(breeder.getMail()).isPresent()){
            return false;
        }

        breederRepository.insert(
                breeder.getName(),
                breeder.getLast_name(),
                breeder.getSecond_last_name(),
                breeder.getUsername(),
                password,
                breeder.getMail(),
                LocalDateTime.now());

        return true;
    }

    public Optional<Breeder> getBreeder(String token){
        Integer idBreeder = commonsService.getIdByToken(token);
        return breederRepository.searchBreeder(idBreeder);
    }

    public Breeder putBreeder(BreederWrapper breeder, String token){
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(commonsService.getIdByToken(token));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(breeder.getPassword());

        if (breeder1.isPresent()){
            breederRepository.updateBreeder(password, commonsService.getIdByToken(token), LocalDateTime.now());
            return breederRepository.searchBreeder(commonsService.getIdByToken(token)).get();
        }

        return null;
    }

    public Breeder softDeleteBreeder(String token){
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(commonsService.getIdByToken(token));

        if (breeder1.isPresent()){
            breederRepository.softDeleteBreeder(true, LocalDateTime.now(), commonsService.getIdByToken(token));
            return breederRepository.searchBreeder(commonsService.getIdByToken(token)).get();
        }

        return null;
    }



}
