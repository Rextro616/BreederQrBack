package com.example.BreederQr.services;

import com.example.BreederQr.Utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
@AllArgsConstructor
@Service
public class CommonsService {
     JwtTokenUtil jwtTokenUtil;

/*    public static String uploadImage(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        try {
            path = path + fileName;
            File newFile = new File(path);
            newFile.createNewFile();
            FileOutputStream keyfile = new FileOutputStream(newFile);
            keyfile.write(file.getBytes());
            keyfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

 */

    public Integer getIdByToken(String token){
        String[] jwtSubject = jwtTokenUtil.getSubject(token).split(",");
        return Integer.parseInt(jwtSubject[0]);
    }


}
