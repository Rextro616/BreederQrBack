package com.example.BreederQr.config.swagger;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BreederPlaceWrapper {
    private MultipartFile image;
    private String name;
    private String address;
    private String register_number;
    private String description;

}
