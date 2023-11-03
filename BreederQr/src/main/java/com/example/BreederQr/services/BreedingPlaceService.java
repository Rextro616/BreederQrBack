package com.example.BreederQr.services;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.BreedingPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreedingPlaceService {
    @Autowired
    BreedingPlaceRepository breedingPlaceRepository;

    Boolean saveBreedingPlace(BreedingPlace breedingPlace, int idBreeder){
        return breedingPlaceRepository.saveBreedingPlaceRepo(breedingPlace.getAddress(), breedingPlace.getDescription(), "rutadellogo", breedingPlace.getName(), breedingPlace.getRegister_number(), idBreeder);
    }
}
