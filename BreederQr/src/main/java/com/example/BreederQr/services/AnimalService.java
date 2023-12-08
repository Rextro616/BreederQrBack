package com.example.BreederQr.services;

import com.example.BreederQr.Utils.QrGenerator;
import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breedingplace.BreederPlaceWrapper;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.repository.AnimalRepository;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AnimalService {
    AnimalRepository animalRepository;
    CommonsService commonsService;
    BreedingPlaceService breedingPlaceService;
    QrGenerator qrGenerator;

    public void createAnimal(AnimalWrapper animal, String token) throws IOException, WriterException {
        Integer idBreeder = commonsService.getIdByToken(token);
        Path path = qrGenerator.generateQRCode(animal);

        String pathImage = String.valueOf(path).replace("http","https");

        animalRepository.insert(
                animal.getName(),
                animal.getSpecie(),
                animal.getBreedingPlace(),
                animal.getBirthday(),
                animal.getDescription(),
                animal.getRegisterNumber(),
                animal.getGender(),
                String.valueOf(pathImage),
                LocalDateTime.now(),
                idBreeder);
    }
    public Optional<List<Animal>> getAllAnimals(String token, Integer where, Integer from){
        Optional<BreedingPlace> breedingPlace = breedingPlaceService.getBreedingPlace(token);
        if (breedingPlace.isPresent()){
            Optional<List<Animal>> animals = animalRepository.getAllAnimals(breedingPlace.get().getId(), where, from);
            if (animals.isPresent()){
                return animals;
            }
        }
        return Optional.empty();
    }

    public Optional<Animal> getAnimalById(Integer id){
        return animalRepository.getAnimalById(id);
    }

    public Boolean putAnimal(AnimalWrapper animalWrapper, String token, Integer id) throws IOException, WriterException {
        Integer idBreeder = commonsService.getIdByToken(token);
        Optional<Animal> animal = getAnimalById(id);

        if (animal.isPresent()){
            Animal animalChange = animal.get();

            Path path = qrGenerator.generateQRCode(animalWrapper);

            String pathImage = String.valueOf(path).replace("http","https");

            animalRepository.updateAnimal(
                    animalChange.getId(),
                    animalWrapper.getName(),
                    animalWrapper.getSpecie(),
                    animalWrapper.getBirthday(),
                    animalWrapper.getDescription(),
                    animalWrapper.getRegisterNumber(),
                    animalWrapper.getGender(),
                    String.valueOf(pathImage),
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

    public Integer getDeletedAnimal(Integer idBreedingPlace){
        return animalRepository.getDeletedAnimal(idBreedingPlace);
    }
}
