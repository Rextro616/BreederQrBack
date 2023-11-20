package com.example.BreederQr.services;

import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AnimalService {
    AnimalRepository animalRepository;
    CommonsService commonsService;
    BreedingPlaceService breedingPlaceService;

    public void createAnimal(AnimalWrapper animal, String token){
        Integer idBreeder = commonsService.getIdByToken(token);
        String path = CommonsService.uploadImage(animal.getQr(), "/Users/rextro/Documents/Github\\ Repository/BreederQrBack/BreederQr/src/main/resources/files/qrs");

        animalRepository.insert(
                animal.getName(),
                animal.getSpecie(),
                animal.getBreedingPlace(),
                animal.getBirthday(),
                animal.getDescription(),
                animal.getRegister_number(),
                animal.getGender(),
                path,
                LocalDateTime.now(),
                idBreeder);
    }
    public Optional<List<Animal>> getAllAnimals(String token){
        Optional<BreedingPlace> breedingPlace = breedingPlaceService.getBreedingPlace(token);
        if (breedingPlace.isPresent()){
            Optional<List<Animal>> animals = animalRepository.getAllAnimals(breedingPlace.get().getId());
            if (animals.isPresent()){
                return animals;
            }
        }
        return Optional.empty();
    }
}
