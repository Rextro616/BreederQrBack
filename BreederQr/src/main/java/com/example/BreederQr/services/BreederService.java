package com.example.BreederQr.services;

import com.example.BreederQr.models.breeder.Breeder;
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

    public Boolean createBreeder(Breeder breeder){
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

    public Optional<Breeder> getBreeder(int idBreeder){
        return breederRepository.searchBreeder(idBreeder);
    }

    public Breeder putBreeder(Breeder breeder){
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(breeder.getId());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(breeder.getPassword());

        if (breeder1.isPresent()){
            breederRepository.updateBreeder(password, breeder.getId(), LocalDateTime.now());
            return breederRepository.searchBreeder(breeder.getId()).get();
        }

        return null;
    }

    public Breeder softDeleteBreeder(Breeder breeder){
        Optional<Breeder> breeder1 = breederRepository.searchBreeder(breeder.getId());

        if (breeder1.isPresent()){
            breederRepository.softDeleteBreeder(true, LocalDateTime.now(), breeder.getId());
            return breederRepository.searchBreeder(breeder.getId()).get();
        }

        return null;
    }



}
