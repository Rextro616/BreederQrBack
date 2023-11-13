package com.example.BreederQr.services;

import com.example.BreederQr.config.swagger.BreederPlaceWrapper;
import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.BreedingPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BreedingPlaceService {
    BreedingPlaceRepository breedingPlaceRepository;
    CommonsService commonsService;

    public void saveBreedingPlace(BreederPlaceWrapper breederPlaceWrapper, String token){
        String path = CommonsService.uploadImage(breederPlaceWrapper.getImage(), "C:\\Users\\flore\\Downloads\\BreederQrBack\\BreederQr\\src\\main\\resources\\files\\logos");
        Integer idBreeder = commonsService.getIdByToken(token);

        breedingPlaceRepository.saveBreedingPlaceRepo(
                breederPlaceWrapper.getAddress(),
                breederPlaceWrapper.getDescription(),
                path,
                breederPlaceWrapper.getName(),
                breederPlaceWrapper.getRegister_number(),
                idBreeder,
                LocalDateTime.now(),
                idBreeder
        );
    }

    public Optional<BreedingPlace> getBreeder(String token){
        Integer idBreeder = commonsService.getIdByToken(token);
        return breedingPlaceRepository.getBreedingPlaceRepo(idBreeder);
    }

    public void putBreedingPlace(String token){

    }

}
