package com.example.BreederQr.services;

import com.example.BreederQr.models.breedingplace.BreederPlaceWrapper;
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
        String path = CommonsService.uploadImage(breederPlaceWrapper.getImage(), "C:\\Users\\6QW95LA_2004\\IdeaProjects\\BreederQrBack\\BreederQr\\src\\main\\resources\\files\\logos\\");
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

    public Optional<BreedingPlace> getBreedingPlace(String token){
        Integer idBreeder = commonsService.getIdByToken(token);
        return breedingPlaceRepository.getBreedingPlaceRepo(idBreeder);
    }

    public Boolean putBreedingPlace(BreederPlaceWrapper breederPlaceWrapper, String token){
        Integer idBreeder = commonsService.getIdByToken(token);
        Optional<BreedingPlace> breedingPlace = getBreedingPlace(token);

        if (breedingPlace.isPresent()){
            BreedingPlace breedingPlace1 = breedingPlace.get();
            CommonsService.deleteImage(breedingPlace1.getLogo());

            String path = CommonsService.uploadImage(breederPlaceWrapper.getImage(), "C:\\Users\\flore\\Downloads\\BreederQrBack\\BreederQr\\src\\main\\resources\\files\\logos\\");
            breedingPlaceRepository.updateBreedingPlace(
                    breedingPlace1.getId(),
                    breederPlaceWrapper.getAddress(),
                    breederPlaceWrapper.getDescription(),
                    path,
                    breederPlaceWrapper.getName(),
                    breederPlaceWrapper.getRegister_number(),
                    LocalDateTime.now(),
                    idBreeder
            );

            return true;
        }

        return false;
    }

    public Boolean deleteBreedingPlace(String token){
        Optional<BreedingPlace> breedingPlace = getBreedingPlace(token);
        Integer idBreeder = commonsService.getIdByToken(token);

        if (breedingPlace.isPresent()){
            BreedingPlace breedingPlace1 = breedingPlace.get();
            CommonsService.deleteImage(breedingPlace1.getLogo());
            breedingPlaceRepository.softDeleteBreedingPlace(
                    true,
                    LocalDateTime.now(),
                    idBreeder,
                    breedingPlace1.getId()
            );
            return true;
        }
        return false;
    }

}
