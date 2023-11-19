package com.example.BreederQr.models.animal;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AnimalWrapper {
    private String name;
    private Integer specie;
    private Integer breedingPlace;
    private String birthday;
    private String description;
    private String register_number;
    private String gender;
    private MultipartFile qr;

}
