package com.example.BreederQr.services;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.laying.Laying;
import com.example.BreederQr.models.laying.LayingWrapper;
import com.example.BreederQr.repository.LayingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LayingService {
    CommonsService commonsService;
    LayingRepository layingRepository;
    public void createLaying(LayingWrapper layingWrapper, String token){
        Integer idBreeder = commonsService.getIdByToken(token);

        layingRepository.insert(
                layingWrapper.getIdAnimal(),
                layingWrapper.getAmount(),
                layingWrapper.getDeads(),
                LocalDateTime.now(),
                idBreeder);
    }
    public Optional<List<Laying>> getAllLayings(Integer idAnimal, Integer where, Integer from){
        if (layingRepository.getAllLayings(idAnimal, where, from).isPresent()){
            return layingRepository.getAllLayings(idAnimal, where, from);
        }
        return Optional.empty();
    }

    public Optional<Laying> getLayingById(Integer id){
        return layingRepository.getLayingById(id);
    }

    public Boolean putLaying(LayingWrapper layingWrapper, String token, Integer id){
        Integer idBreeder = commonsService.getIdByToken(token);
        Optional<Laying> laying = getLayingById(id);

        if (laying.isPresent()){
            Laying layingChange = laying.get();

           layingRepository.updateLaying(
                    layingChange.getId(),
                    layingWrapper.getAmount(),
                    layingWrapper.getDeads(),
                    LocalDateTime.now(),
                    idBreeder
            );

            return true;
        }

        return false;
    }

    public Boolean deleteLaying(String token, Integer id){
        Integer idBreeder = commonsService.getIdByToken(token);

        Optional<Laying> laying = getLayingById(id);

        if (laying.isPresent()){
            Laying laying1 = laying.get();

            layingRepository.softDeleteLaying(
                    true,
                    LocalDateTime.now(),
                    idBreeder,
                    laying1.getId()
            );
            return true;
        }
        return false;
    }

    public Integer getAmount(Integer idBreedingPlace){
        return layingRepository.getAmount(idBreedingPlace);
    }

    public Integer getDeads(Integer idBreedingPlace){
        Integer deads = layingRepository.getDeads(idBreedingPlace);

        return deads;
    }
}
