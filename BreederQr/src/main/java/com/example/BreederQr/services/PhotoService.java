package com.example.BreederQr.services;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.models.photo.Photo;
import com.example.BreederQr.models.photo.PhotoWrapper;
import com.example.BreederQr.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PhotoService {
    PhotoRepository photoRepository;
    CommonsService commonsService;

    public void postPhoto(String token, PhotoWrapper photoWrapper){
        Integer idBreeder = commonsService.getIdByToken(token);
        String path = CommonsService.uploadImage(photoWrapper.getPhoto(),"/Users/rextro/Documents/Github Repository/BreederQrBack/BreederQr/src/main/resources/files/Animals/");

        photoRepository.insert(
                path,
                photoWrapper.getIdAnimal(),
                LocalDateTime.now(),
                idBreeder
                );
    }

    public Optional<List<Photo>> getPhoto(Integer idBreedingPlace, Integer where, Integer from){
        return photoRepository.getPhoto(idBreedingPlace, where, from);
    }

    public Boolean deletePhoto(Integer idPhoto, String token){
        Integer idBreeder = commonsService.getIdByToken(token);
        Optional<Photo> photo = photoRepository.getPhotoById(idPhoto);

        if (photo.isPresent()){
            CommonsService.deleteImage(photo.get().getPhoto());

            photoRepository.softDeletePhoto(
                    true,
                    LocalDateTime.now(),
                    idBreeder,
                    idPhoto
            );
            return true;
        }

        return false;
    }
}
