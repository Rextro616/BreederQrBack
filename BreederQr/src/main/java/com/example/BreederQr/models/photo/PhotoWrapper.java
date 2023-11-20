package com.example.BreederQr.models.photo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PhotoWrapper {
    private Integer idAnimal;
    private MultipartFile photo;
}
