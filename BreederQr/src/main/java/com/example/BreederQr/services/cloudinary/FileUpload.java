package com.example.BreederQr.services.cloudinary;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileUpload {
    String uploadFile(MultipartFile multipartFile) throws IOException;

    String deleteFile(String idImg) throws IOException;
}