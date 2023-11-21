package com.example.BreederQr.services;

import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breedingplace.BreederPlaceWrapper;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        String path = CommonsService.uploadImage(animal.getQr(), "/Users/rextro/Documents/Github Repository/BreederQrBack/BreederQr/src/main/resources/files/animals/");

        animalRepository.insert(
                animal.getName(),
                animal.getSpecie(),
                animal.getBreedingPlace(),
                animal.getBirthday(),
                animal.getDescription(),
                animal.getRegisterNumber(),
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

    public Optional<Animal> getAnimalById(Integer id){
        return animalRepository.getAnimalById(id);
    }

    public Boolean putAnimal(AnimalWrapper animalWrapper, String token, Integer id){
        Integer idBreeder = commonsService.getIdByToken(token);
        Optional<Animal> animal = getAnimalById(id);

        if (animal.isPresent()){
            Animal animalChange = animal.get();
            CommonsService.deleteImage(animalChange.getQr());

            String path = CommonsService.uploadImage(animalWrapper.getQr(), "/Users/rextro/Documents/Github Repository/BreederQrBack/BreederQr/src/main/resources/files/animals/");
            animalRepository.updateAnimal(
                    animalChange.getId(),
                    animalWrapper.getName(),
                    animalWrapper.getSpecie(),
                    animalWrapper.getBirthday(),
                    animalWrapper.getDescription(),
                    animalWrapper.getRegisterNumber(),
                    animalWrapper.getGender(),
                    path,
                    LocalDateTime.now(),
                    idBreeder
            );

            return true;
        }

        return false;
    }

    public Boolean deleteAnimal(String token, Integer id){
        Integer idBreeder = commonsService.getIdByToken(token);

        Optional<Animal> animal = getAnimalById(id);

        if (animal.isPresent()){
            Animal animal1 = animal.get();
            CommonsService.deleteImage(animal1.getQr());
            animalRepository.softDeleteAnimal(
                    true,
                    LocalDateTime.now(),
                    idBreeder,
                    animal1.getId()
            );
            return true;
        }
        return false;
    }
}
